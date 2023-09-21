package pl.maciejak.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.service.DonationService;
import pl.maciejak.charity.service.InstitutionService;

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
    public String home(Model model) {
        model.addAttribute("donationsCount", donationService.donationsCount());
        model.addAttribute("bagsCount", donationService.bagsCount());
        model.addAttribute("institutions", institutionService.findAll());
        return "home";
    }

}