package tech.skylerwebdev.johannsark.services;

import tech.skylerwebdev.johannsark.models.Category;

import java.util.List;

public interface CategoryService {
  List<Category> findAll();

  Category findRoleById(long id);

  void delete(long id);

  Category save(Category role);

  Category findByName(String name);

  Category update(long id, Category role);
}
