package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {
    Optional<Category> findById(Long id);

    List<Category> findAll();

    void save(Category category);

    void delete(Category category);
}
