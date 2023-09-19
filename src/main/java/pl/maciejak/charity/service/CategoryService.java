package pl.maciejak.charity.service;

import org.springframework.stereotype.Service;
import pl.maciejak.charity.entity.Category;
import pl.maciejak.charity.repository.CategoryRepository;
import pl.maciejak.charity.service.interfaces.CategoryServiceInterface;

import java.util.List;

@Service
public class CategoryService implements CategoryServiceInterface {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
