package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.Category;

import java.util.List;

public interface CategoryServiceInterface {
    Category findById(Integer id);

    List<Category> findAll();

    void save(Category category);

    void delete(Category category);
}
