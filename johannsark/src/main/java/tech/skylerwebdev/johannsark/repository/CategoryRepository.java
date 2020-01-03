package tech.skylerwebdev.johannsark.repository;

import org.springframework.data.repository.CrudRepository;
import tech.skylerwebdev.johannsark.models.Category;

public interface CategoryRepository extends CrudRepository<Category, String> {
}
