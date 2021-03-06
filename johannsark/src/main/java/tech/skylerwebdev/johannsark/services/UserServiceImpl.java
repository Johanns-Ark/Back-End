package tech.skylerwebdev.johannsark.services;

import tech.skylerwebdev.johannsark.exceptions.ResourceFoundException;
import tech.skylerwebdev.johannsark.exceptions.ResourceNotFoundException;
import tech.skylerwebdev.johannsark.logging.Loggable;
import tech.skylerwebdev.johannsark.models.Role;
import tech.skylerwebdev.johannsark.models.User;
import tech.skylerwebdev.johannsark.models.UserRoles;
import tech.skylerwebdev.johannsark.models.UserEmail;
import tech.skylerwebdev.johannsark.repository.RoleRepository;
import tech.skylerwebdev.johannsark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Loggable
@Service(value = "userService")
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userrepos;

    @Autowired
    private RoleRepository rolerepos;

    public User findUserById(long uuid) throws ResourceNotFoundException
    {
        return userrepos.findById(uuid)
                        .orElseThrow(() -> new ResourceNotFoundException("User id " + uuid + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username,
                                           Pageable pageable)
    {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase(),
                                                            pageable);
    }

    @Override
    public List<User> findAll(Pageable pageable)
    {
        List<User> list = new ArrayList<>();
        userrepos.findAll(pageable)
                 .iterator()
                 .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long uuid)
    {
        userrepos.findById(uuid)
                 .orElseThrow(() -> new ResourceNotFoundException("User uuid " + uuid + " not found!"));
        userrepos.deleteById(uuid);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        if (userrepos.findByUsername(user.getUsername().toLowerCase()) != null)
        {
            throw new ResourceFoundException(user.getUsername() + " is already taken!");
        }

        User newUser = new User();
        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());

        ArrayList<UserRoles> newRoles = new ArrayList<>();
        for (UserRoles ur : user.getUserroles())
        {
            long id = ur.getRole()
                        .getRoleid();
            Role role = rolerepos.findById(id)
                                 .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
            newRoles.add(new UserRoles(newUser,
                                       ur.getRole()));
        }
        newUser.setUserroles(newRoles);

        for (UserEmail ue : user.getUserEmails())
        {
            newUser.getUserEmails()
                   .add(new UserEmail(newUser,
                                      ue.getUseremail()));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(User user,
                       long uuid,
                       boolean isAdmin)
    {
        Authentication authentication = SecurityContextHolder.getContext()
                                                             .getAuthentication();

        User authenticatedUser = userrepos.findByUsername(authentication.getName());

        if (uuid == authenticatedUser.getUuid() || isAdmin)
        {
            User currentUser = findUserById(uuid);

            if (user.getUsername() != null)
            {
                currentUser.setUsername(user.getUsername().toLowerCase());
            }

            if (user.getPassword() != null)
            {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }

            if (user.getPrimaryemail() != null)
            {
                currentUser.setPrimaryemail(user.getPrimaryemail().toLowerCase());
            }

            if (user.getUserroles()
                    .size() > 0)
            {
                throw new ResourceFoundException("User Roles are not updated through User. See endpoint POST: users/user/{uuid}/role/{roleid}");
            }

            if (user.getUserEmails()
                    .size() > 0)
            {
                for (UserEmail ue : user.getUserEmails())
                {
                    currentUser.getUserEmails()
                               .add(new UserEmail(currentUser,
                                                  ue.getUseremail()));
                }
            }

            return userrepos.save(currentUser);
        } else
        {
            throw new ResourceNotFoundException(uuid + " Not current user");
        }
    }

    @Transactional
    @Override
    public void deleteUserRole(long uuid,
                               long roleid)
    {
        userrepos.findById(uuid)
                 .orElseThrow(() -> new ResourceNotFoundException("User uuid " + uuid + " not found!"));
        rolerepos.findById(roleid)
                 .orElseThrow(() -> new ResourceNotFoundException("Role uuid " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(uuid,
                                          roleid)
                     .getCount() > 0)
        {
            rolerepos.deleteUserRoles(uuid,
                                      roleid);
        } else
        {
            throw new ResourceNotFoundException("Role and User Combination Does Not Exists");
        }
    }

    @Transactional
    @Override
    public void addUserRole(long uuid,
                            long roleid)
    {
        userrepos.findById(uuid)
                 .orElseThrow(() -> new ResourceNotFoundException("User uuid " + uuid + " not found!"));
        rolerepos.findById(roleid)
                 .orElseThrow(() -> new ResourceNotFoundException("Role uuid " + roleid + " not found!"));

        if (rolerepos.checkUserRolesCombo(uuid,
                                          roleid)
                     .getCount() <= 0)
        {
            rolerepos.insertUserRoles(uuid,
                                      roleid);
        } else
        {
            throw new ResourceFoundException("Role and User Combination Already Exists");
        }
    }
}
