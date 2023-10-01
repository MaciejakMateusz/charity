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
    private Page<User> allUsers;
    private User foundUser;
    private boolean filterEngaged;
    private String emailFilter;

    private final UserService userService;
    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String users(Model model) {
        filterEngaged = false;
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
        if(filterEngaged) {
            return "redirect:/admin/dashboard/findByEmail";
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/incrementPage")
    private String incrementPageNumber() {
        if (allUsers.hasNext()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() + 1, PAGE_SIZE);
        }
        if(filterEngaged) {
            return "redirect:/admin/dashboard/findByEmail";
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/decrementPage")
    private String decrementPage() {
        if (this.pageable.hasPrevious()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() - 1, PAGE_SIZE);
        }
        if(filterEngaged) {
            return "redirect:/admin/dashboard/findByEmail";
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/findById")
    private String findById(@RequestParam Long id, Model model) {
        model.addAttribute("filterEngaged", true);
        if (!userService.existsById(id)) {
            this.foundUser = null;
            model.addAttribute("userNotFound", true);
        } else {
            this.foundUser = userService.findById(id);
            model.addAttribute("foundUser", foundUser);
        }
        return "admin/dashboard";
    }

    @GetMapping("/findById")
    private String findById(Model model) {
        model.addAttribute("filterEngaged", true);
        if (this.foundUser == null) {
            model.addAttribute("userNotFound", true);
        } else {
            model.addAttribute("foundUser", this.foundUser);
        }
        return "admin/dashboard";
    }

    @PostMapping("/findByEmail")
    private String findByEmail(@RequestParam String email, Model model) {
        filterEngaged = true;
        this.emailFilter = email;
        this.allUsers = userService.findByPartialEmail(email, this.pageable);
        if (this.allUsers == null || this.allUsers.isEmpty()) {
            model.addAttribute("userNotFound", true);
        } else {
            emailFilterModelAttributes(model);
        }
        return "admin/dashboard";
    }

    @GetMapping("/findByEmail")
    private String findByEmail(Model model) {
        filterEngaged = true;
        this.allUsers = userService.findByPartialEmail(this.emailFilter, this.pageable);
        if (this.allUsers.isEmpty()) {
            model.addAttribute("userNotFound", true);
        } else {
            emailFilterModelAttributes(model);
        }
        return "admin/dashboard";
    }

    private void emailFilterModelAttributes(Model model) {
        int totalPages = this.allUsers.getTotalPages();
        model.addAttribute("pageable", this.pageable);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("foundByEmail", true);
        model.addAttribute("partialEmail", this.emailFilter);
        model.addAttribute("users", this.allUsers.stream().toList());
    }

    private Page<User> getAllUsers() {
        return userService.findByRoles("ROLE_USER", this.pageable);
    }
}