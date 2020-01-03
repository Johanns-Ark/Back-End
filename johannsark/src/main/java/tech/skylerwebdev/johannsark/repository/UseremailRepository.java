package tech.skylerwebdev.johannsark.repository;

import tech.skylerwebdev.johannsark.models.UserEmail;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UseremailRepository extends CrudRepository<UserEmail, Long>
{
    List<UserEmail> findAllByUser_Username(String name);
}
