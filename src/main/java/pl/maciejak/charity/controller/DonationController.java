package pl.maciejak.charity.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.Category;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.service.CategoryService;
import pl.maciejak.charity.service.DonationService;

import java.util.List;

@Controller
@RequestMapping("/form")
public class DonationController {

    private final CategoryService categoryService;
    private final DonationService donationService;

    public DonationController(CategoryService categoryService, DonationService donationService) {
        this.categoryService = categoryService;
        this.donationService = donationService;
    }


    @GetMapping
    public String form(Model model) {
        model.addAttribute("donation", new Donation());
        return "form/form";
    }

    @PostMapping
    public String form(@Valid Donation donation, BindingResult br) {
        if(br.hasErrors()) {
            return "form/form";
        }
        donationService.save(donation);
        return "form/form-confirmation";
    }

    @ModelAttribute("categories")
    private List<Category> getCategories() {
        return categoryService.findAll();
    }
}