package pl.maciejak.charity.controller.donations.DonationDetailsController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.service.DonationService;

@Controller
@RequestMapping("/donations/details")
@Slf4j
public class DonationDetailsController {

    private final DonationService donationService;

    public DonationDetailsController(DonationService donationService) {
        this.donationService = donationService;
    }

    @PostMapping
    public String donationDetails(@RequestParam long id, Model model) {
        Donation donation = donationService.findById(id);
        model.addAttribute("donation", donation);
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
}