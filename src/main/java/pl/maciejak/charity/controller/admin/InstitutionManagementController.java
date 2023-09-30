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
        return "redirect:/admin/institutions";
    }

    @PostMapping("/incrementPage")
    private String incrementPageNumber() {
        if (allInstitutions.hasNext()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() + 1, PAGE_SIZE);
        }
        return "redirect:/admin/institutions";
    }

    @PostMapping("/decrementPage")
    private String decrementPage() {
        if (this.pageable.hasPrevious()) {
            this.pageable = PageRequest.of(this.pageable.getPageNumber() - 1, PAGE_SIZE);
        }
        return "redirect:/admin/institutions";
    }

    private Page<Institution> getAllInstitutions() {
        return institutionService.findAll(this.pageable);
    }
}