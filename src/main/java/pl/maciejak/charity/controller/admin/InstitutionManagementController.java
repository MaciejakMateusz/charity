package pl.maciejak.charity.controller.admin;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.service.InstitutionService;

@Controller
@RequestMapping("/admin/institutions")
public class InstitutionManagementController {

    private static final int PAGE_SIZE = 10;
    private Pageable pageable = PageRequest.of(0, PAGE_SIZE);
    private Page<Institution> allInstitutions;
    private Institution foundInstitution;
    private boolean filterEngaged;
    private String emailFilter;

    private final InstitutionService institutionService;

    public InstitutionManagementController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    private Institution institution;

    @GetMapping
    public String institutions(Model model) {
        this.allInstitutions = getAllInstitutions();
        int totalPages = allInstitutions.getTotalPages();
        model.addAttribute("pageable", this.pageable);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("institutions", allInstitutions.stream().toList());
        return "admin/institutions/list";
    }

    @PostMapping("/show")
    public String show(@RequestParam Long id, Model model) {
        this.institution = institutionService.findById(id);
        model.addAttribute("institution", this.institution);
        return "admin/institutions/show";
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("institution", this.institution);
        return "admin/institutions/show";
    }

    @PostMapping("/edit")
    public String edit(@RequestParam Long id, Model model) {
        this.institution = institutionService.findById(id);
        model.addAttribute("institution", this.institution);
        return "admin/institutions/edit";
    }

    @PostMapping("/update")
    public String edit(@Valid Institution institution, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "admin/institutions/edit";
        }
        institutionService.save(institution);
        model.addAttribute("isUpdated", true);
        return "admin/institutions/edit";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("institution", this.institution);
        return "admin/institutions/edit";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        this.institution = institutionService.findById(id);
        model.addAttribute("institution", this.institution);
        return "admin/institutions/delete";
    }

    @PostMapping("/remove")
    public String delete(Institution institution, Model model) {
        institutionService.delete(institution);
        model.addAttribute("isRemoved", true);
        return "admin/institutions/delete";
    }

    @GetMapping("/delete")
    public String delete(Model model) {
        model.addAttribute("institution", this.institution);
        return "admin/institutions/delete";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("institution", new Institution());
        return "admin/institutions/add";
    }

    @PostMapping("/add")
    public String add(@Valid Institution institution, BindingResult br, Model model) {
        if (br.hasErrors()) {
            return "admin/institutions/add";
        }
        institutionService.save(institution);
        model.addAttribute("isCreated", true);
        model.addAttribute("institution", new Institution());
        return "admin/institutions/add";
    }

    @PostMapping("/page")
    private String setPageNumber(@RequestParam int pageNumber) {
        if (pageNumber >= 0) {
            this.pageable = PageRequest.of(pageNumber, PAGE_SIZE);
        }
        if (filterEngaged) {
            return "redirect:/admin/institutions/findByEmail";
        }
        return "redirect:/admin/institutions";
    }

    @PostMapping("/incrementPage")
    private String incrementPageNumber() {
        if (allInstitutions.hasNext()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() + 1, PAGE_SIZE);
        }
        if (filterEngaged) {
            return "redirect:/admin/institutions/findByEmail";
        }
        return "redirect:/admin/institutions";
    }

    @PostMapping("/decrementPage")
    private String decrementPage() {
        if (this.pageable.hasPrevious()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() - 1, PAGE_SIZE);
        }
        if (filterEngaged) {
            return "redirect:/admin/institutions/findByEmail";
        }
        return "redirect:/admin/institutions";
    }

    @PostMapping("/findById")
    private String findById(@RequestParam Long id, Model model) {
        model.addAttribute("filterEngaged", true);
        if (!institutionService.existsById(id)) {
            this.foundInstitution = null;
            model.addAttribute("institutionNotFound", true);
        } else {
            this.foundInstitution = institutionService.findById(id);
            model.addAttribute("foundInstitution", foundInstitution);
        }
        return "admin/institutions/list";
    }

    @GetMapping("/findById")
    private String findById(Model model) {
        model.addAttribute("filterEngaged", true);
        if (this.foundInstitution == null) {
            model.addAttribute("institutionNotFound", true);
        } else {
            model.addAttribute("foundInstitution", this.foundInstitution);
        }
        return "admin/institutions/list";
    }

    @PostMapping("/findByEmail")
    private String findByEmail(@RequestParam String email, Model model) {
        this.filterEngaged = true;
        this.emailFilter = email;
        this.allInstitutions = institutionService.findByPartialEmail(email, this.pageable);
        if (this.allInstitutions == null || this.allInstitutions.isEmpty()) {
            model.addAttribute("institutionNotFound", true);
        } else {
            emailFilterModelAttributes(model);
        }
        return "admin/institutions/list";
    }

    @GetMapping("/findByEmail")
    private String findByEmail(Model model) {
        this.filterEngaged = true;
        this.allInstitutions = institutionService.findByPartialEmail(this.emailFilter, this.pageable);
        if (this.allInstitutions.isEmpty()) {
            model.addAttribute("institutionsNotFound", true);
        } else {
            emailFilterModelAttributes(model);
        }
        return "admin/institutions/list";
    }

    private void emailFilterModelAttributes(Model model) {
        int totalPages = this.allInstitutions.getTotalPages();
        model.addAttribute("pageable", this.pageable);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("foundByEmail", true);
        model.addAttribute("partialEmail", this.emailFilter);
        model.addAttribute("institutions", this.allInstitutions.stream().toList());
    }

    private Page<Institution> getAllInstitutions() {
        return institutionService.findAll(this.pageable);
    }
}