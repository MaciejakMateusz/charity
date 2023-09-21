package pl.maciejak.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.Category;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.repository.CategoryRepository;

import java.util.List;

@Controller
@RequestMapping("/form")
public class DonationController {

    private final CategoryRepository categoryRepository;

    public DonationController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String form(Model model) {
        model.addAttribute("donation", new Donation());
        return "form/form";
    }

    @PostMapping
    public String form() {
        return "form/form-confirmation";
    }

    @ModelAttribute("categories")
    private List<Category> getCategories() {
        return categoryRepository.findAll();
    }
}
