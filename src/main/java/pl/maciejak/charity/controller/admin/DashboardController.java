package pl.maciejak.charity.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/admin/dashboard")
@Slf4j
public class DashboardController {

    private static final int PAGE_SIZE = 10;
    private Pageable pageable = PageRequest.of(0, PAGE_SIZE);

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model, @RequestParam int page) {
        Page<User> allUsers = getAllUsers();
        int totalPages = allUsers.getTotalPages();
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        model.addAttribute("pageable", pageable);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("users", allUsers.stream().toList());
        return "admin/dashboard";
    }

    @PostMapping("/page")
    private String setPageNumber(@RequestParam int pageNumber) {
        if (pageNumber >= 0) {
            this.pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/incrementPage")
    private String incrementPageNumber() {
        Page<User> allUsers = getAllUsers();
        if (allUsers.hasNext()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() + 1, PAGE_SIZE);
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/decrementPage")
    private String decrementPage() {
        if (this.pageable.hasPrevious()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() - 1, PAGE_SIZE);
        }
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/idFilter")
    private String findById(Model model, @RequestParam Long id) {
        User user = userService.findById(id);
        model.addAttribute("filterEngaged", true);
        if (user == null) {
            model.addAttribute("userNotFound", true);
        } else {
            model.addAttribute("foundUser", user);
        }
        return "admin/dashboard";
    }

    @GetMapping("/emailFilter")
    private String findByEmail(Model model, @RequestParam String email) {
        Page<User> allUsers = userService.findByPartialEmail(email, "ROLE_USER", this.pageable);
        if (allUsers.isEmpty()) {
            model.addAttribute("userNotFound", true);
        } else {
            int totalPages = allUsers.getTotalPages();
            model.addAttribute("pageable", this.pageable);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("foundByEmail", true);
            model.addAttribute("partialEmail", email);
            model.addAttribute("users", allUsers.stream().toList());
        }
        return "admin/dashboard";
    }

    private Page<User> getAllUsers() {
        return userService.findByRoles("ROLE_USER", this.pageable);
    }
}