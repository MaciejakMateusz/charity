package pl.maciejak.charity.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.service.UserService;

@Controller
@RequestMapping("/admin/dashboard")
@Slf4j
public class DashboardController {

    private static final int PAGE_SIZE = 10;
    private Pageable pageable = PageRequest.of(0, PAGE_SIZE);
    private Page<User> allUsers;

    private final UserService userService;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String users(Model model) {
        this.allUsers = getAllUsers();
        int totalPages = this.allUsers.getTotalPages();
        model.addAttribute("pageable", this.pageable);
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

    @GetMapping("/{id}")
    private String findById(Model model, @PathVariable Long id) {
        User user = userService.findById(id);
        model.addAttribute("filterEngaged", true);
        if (user == null) {
            model.addAttribute("userNotFound", true);
        } else {
            model.addAttribute("foundUser", user);
        }
        return "admin/dashboard";
    }

    @GetMapping("/{emailFilter}")
    private String findByEmail(Model model, @PathVariable String emailFilter) {
        this.allUsers = userService.findByPartialEmail(emailFilter, "ROLE_USER", this.pageable);
        if (this.allUsers.isEmpty()) {
            model.addAttribute("userNotFound", true);
        } else {
            int totalPages = this.allUsers.getTotalPages();
            model.addAttribute("pageable", this.pageable);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("foundByEmail", true);
            model.addAttribute("partialEmail", emailFilter);
            model.addAttribute("users", this.allUsers.stream().toList());
        }
        return "admin/dashboard";
    }

    private Page<User> getAllUsers() {
        return userService.findByRoles("ROLE_USER", this.pageable);
    }
}