package pl.maciejak.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.service.DonationService;
import pl.maciejak.charity.service.InstitutionService;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;

    public HomeController(InstitutionService institutionService, DonationService donationService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @ModelAttribute("institutions")
    private List<Institution> getInstitutions() {
        return institutionService.findAll();
    }

    @ModelAttribute("donationsCount")
    private Integer getDonationsCount() {
        return donationService.donationsCount();
    }

}