package tech.skylerwebdev.johannsark.repository;

import tech.skylerwebdev.johannsark.models.Role;
import tech.skylerwebdev.johannsark.view.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends CrudRepository<Role, Long>
{
    @Query(value = "SELECT COUNT(*) as count FROM userroles WHERE uuid = :uuid AND roleid = :roleid",
           nativeQuery = true)
    JustTheCount checkUserRolesCombo(long uuid,
                                     long roleid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM UserRoles WHERE uuid = :uuid AND roleid = :roleid")
    void deleteUserRoles(long uuid,
                         long roleid);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO UserRoles(uuid, roleid) VALUES (:uuid, :roleid)",
           nativeQuery = true)
    void insertUserRoles(long uuid,
                         long roleid);

    Role findByNameIgnoreCase(String name);

    @Transactional
    @Modifying
    // user Role instead of roles in order to use Hibernate SQL
    @Query(value = "UPDATE Role SET NAME = :name WHERE roleid = :roleid")
    void updateRoleName(long roleid,
                        String name);
}
