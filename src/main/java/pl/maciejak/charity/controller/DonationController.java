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
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.service.CategoryService;
import pl.maciejak.charity.service.DonationService;
import pl.maciejak.charity.service.InstitutionService;

import java.util.List;

@Controller
@RequestMapping("/form")
public class DonationController {

    private final CategoryService categoryService;
    private final DonationService donationService;
    private final InstitutionService institutionService;

    public DonationController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
    }


    @GetMapping
    public String form(Model model) {
        model.addAttribute("donation", new Donation());
        return "form/form";
    }

    @PostMapping
    public String form(@Valid Donation donation, BindingResult br, Model model) {
        if (br.hasErrors()) {
            model.addAttribute("validFormInputs", false);
            return "form/form";
        }
        donationService.save(donation);
        return "form/form-confirmation";
    }

    @ModelAttribute("categories")
    private List<Category> getCategories() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    private List<Institution> getInstitutions() {
        return institutionService.findAll();
    }
}