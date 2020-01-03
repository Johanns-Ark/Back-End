package tech.skylerwebdev.johannsark.services;

import org.springframework.stereotype.Service;
import tech.skylerwebdev.johannsark.logging.Loggable;
import tech.skylerwebdev.johannsark.models.Category;

import java.util.List;

@Loggable
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {
  @Override
  public List<Category> findAll() {
    return null;
  }

  @Override
  public Category findRoleById(long id) {
    return null;
  }

  @Override
  public void delete(long id) {

  }

  @Override
  public Category save(Category role) {
    return null;
  }

  @Override
  public Category findByName(String name) {
    return null;
  }

  @Override
  public Category update(long id, Category role) {
    return null;
  }
}
