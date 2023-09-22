package pl.maciejak.charity.controller.rest;

import org.springframework.web.bind.annotation.*;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.service.InstitutionService;

@RestController
@RequestMapping("/api/institution")
public class InstitutionRestController {
    private final InstitutionService institutionService;

    public InstitutionRestController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/{id}")
    public Institution getInstitutionById(@PathVariable Integer id) {
        return institutionService.findById(id);
    }
}
