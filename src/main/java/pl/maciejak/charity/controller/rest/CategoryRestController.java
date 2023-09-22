package pl.maciejak.charity.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.maciejak.charity.entity.Category;
import pl.maciejak.charity.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.findById(id);
    }
}
