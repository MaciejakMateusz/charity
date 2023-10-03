package pl.maciejak.charity.controller.donations.DonationDetailsController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.exception.GeneralException;
import pl.maciejak.charity.service.DonationService;

@Controller
@RequestMapping("/donations/details")
@Slf4j
public class DonationDetailsController {

    private final DonationService donationService;

    public DonationDetailsController(DonationService donationService) {
        this.donationService = donationService;
    }

    private Donation currentDonation;

    @PostMapping
    public String donationDetails(@RequestParam long id, Model model) {
        try {
            this.currentDonation = donationService.findById(id);
        } catch (GeneralException e) {
            model.addAttribute("donationNotFound", true);
            model.addAttribute("error", e.getMessage());
            return "donations/donations";
        }
        model.addAttribute("donation", currentDonation);
        return "donations/details/details";
    }

    @PostMapping("/update-status")
    public String updateStatus(Donation donation) {
        donationService.updateStatus(donation);
        return "donations/details/success-update";
    }

    @PostMapping("/archive")
    public String archive(Donation donation) {
        donationService.archive(donation);
        return "donations/details/success-archive";
    }

    @GetMapping
    public String donation(Model model) {
        model.addAttribute("donation", this.currentDonation);
        return "donations/details/details";
    }
}