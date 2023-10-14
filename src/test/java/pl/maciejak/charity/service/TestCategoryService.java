package pl.maciejak.charity.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
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

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@TestPropertySource(locations = "classpath:test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCategoryService {

    @Autowired
    private CategoryServiceInterface categoryService;

    @Test
    public void shouldFindById() {
        Category category = categoryService.findById(3L);
        assertEquals("zabawki", category.getName());
    }

    @Test
    public void shouldFindAll() {
        List<Category> categories = categoryService.findAll();
        assertEquals(5, categories.size());
    }
}
