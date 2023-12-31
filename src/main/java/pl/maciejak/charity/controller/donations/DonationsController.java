package pl.maciejak.charity.controller.donations;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.DonationService;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/donations")
@Slf4j
public class DonationsController {

    private final UserService userService;
    private final DonationService donationService;

    public DonationsController(UserService userService, DonationService donationService) {
        this.userService = userService;
        this.donationService = donationService;
    }

    @GetMapping
    public String donations(Model model) {
        model.addAttribute("donations",
                donationService.findAllByUserAndArchived(getSessionUser(), false));
        return "donations/donations";
    }

    @GetMapping("/archived")
    public String archived(Model model) {
        model.addAttribute("archivedDonations",
                donationService.findAllByUserAndArchived(getSessionUser(), true));
        return "donations/archived/archived-donations";
    }

    private User getSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.findByUsername(username);
    }
}