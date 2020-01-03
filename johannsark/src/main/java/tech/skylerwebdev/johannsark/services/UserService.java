package tech.skylerwebdev.johannsark.services;

import tech.skylerwebdev.johannsark.models.User;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService
{
    List<User> findAll(Pageable pageable);

    List<User> findByNameContaining(String username,
                                    Pageable pageable);

    User findUserById(long uuid);

    User findByName(String name);

    void delete(long uuid);

    User save(User user);

    User update(User user,
                long uuid,
                boolean isAdmin);

    void deleteUserRole(long uuid,
                        long roleid);

    void addUserRole(long uuid,
                     long roleid);
}
