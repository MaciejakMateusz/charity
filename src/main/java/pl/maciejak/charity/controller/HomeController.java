package pl.maciejak.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.service.DonationService;
import pl.maciejak.charity.service.EmailService;
import pl.maciejak.charity.service.InstitutionService;

@Controller
@RequestMapping("/")
public class HomeController {

    private final InstitutionService institutionService;
    private final DonationService donationService;
    private final EmailService emailService;

    public HomeController(InstitutionService institutionService, DonationService donationService, EmailService emailService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.emailService = emailService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("donationsCount", donationService.donationsCount());
        model.addAttribute("bagsCount", donationService.bagsCount());
        model.addAttribute("institutions", institutionService.findAll());
        return "home";
    }

    @PostMapping("/contact-form")
    public String contact(@RequestParam String name,
                          @RequestParam String surname,
                          @RequestParam String email,
                          @RequestParam String message) {
        emailService.contactForm(email, "Wiadomość od " + name + " " + surname, message);
        return "redirect:/";
    }

}