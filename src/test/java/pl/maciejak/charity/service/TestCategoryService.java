package pl.maciejak.charity.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import pl.maciejak.charity.entity.Category;
import pl.maciejak.charity.service.interfaces.CategoryServiceInterface;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCategoryService {

    @Autowired
    private CategoryServiceInterface categoryService;

    @Test
    @Order(1)
    public void shouldFindById() {
        Category category = categoryService.findById(3L).orElse(new Category());
        assertEquals("zabawki", category.getName());
    }

    @Test
    @Order(2)
    public void shouldFindAll() {
        List<Category> categories = categoryService.findAll();
        assertEquals(5, categories.size());
    }

    @Test
    @Order(3)
    public void shouldSaveCategory() {
        Category category = new Category();
        category.setName("Test category");
        categoryService.save(category);
        Category savedCategory = categoryService.findById(6L).orElse(new Category());
        assertEquals("Test category", savedCategory.getName());
    }

    @Test
    @Order(4)
    public void shouldDeleteCategory() {
        Category savedCategory = categoryService.findById(6L).orElse(new Category());
        assertEquals("Test category", savedCategory.getName());
        categoryService.delete(savedCategory);
        Category category = categoryService.findById(6L).orElse(null);
        assertNull(category);
    }
}