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
@RequestMapping("/admin")
@Slf4j
public class DashboardController {

    private static final int PAGE_SIZE = 10;
    private Pageable pageable = PageRequest.of(0, PAGE_SIZE);
    private final UserService userService;
    private Page<User> allUsers;

    public DashboardController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/dashboard")
    public String users(Model model) {
        this.allUsers = getAllUsers();
        int totalPages = allUsers.getTotalPages();
        model.addAttribute("pageable", this.pageable);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("users", allUsers.stream().toList());
        return "admin/dashboard";
    }

    @PostMapping("/dashboard/page")
    private String setPageNumber(@RequestParam int pageNumber) {
        if (pageNumber >= 0) {
            this.pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/dashboard/incrementPage")
    private String incrementPageNumber() {
        if(allUsers.hasNext()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() + 1, PAGE_SIZE);
        }
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/dashboard/decrementPage")
    private String decrementPage() {
        if (this.pageable.hasPrevious()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() - 1, PAGE_SIZE);
        }
        return "redirect:/admin/dashboard";
    }

    private Page<User> getAllUsers() {
        return userService.findByRoles("ROLE_USER", pageable);
    }
}