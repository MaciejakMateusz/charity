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
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.CategoryService;
import pl.maciejak.charity.service.DonationService;
import pl.maciejak.charity.service.InstitutionService;
import pl.maciejak.charity.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/form")
public class DonationController {

    private final CategoryService categoryService;
    private final DonationService donationService;
    private final InstitutionService institutionService;
    private final UserService userService;

    public DonationController(CategoryService categoryService, DonationService donationService, InstitutionService institutionService, UserService userService) {
        this.categoryService = categoryService;
        this.donationService = donationService;
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @GetMapping
    public String form(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("donation", new Donation());
        return "form/form";
    }

    @PostMapping
    public String form(@Valid Donation donation, BindingResult br, Model model, Principal principal) {
        if (br.hasErrors()) {
            model.addAttribute("validFormInputs", false);
            return "form/form";
        }
        donationService.save(donation, principal);
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