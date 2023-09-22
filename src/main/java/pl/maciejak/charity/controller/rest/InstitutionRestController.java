package pl.maciejak.charity.controller.rest;

import org.springframework.web.bind.annotation.*;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.service.InstitutionService;

@RestController
@RequestMapping("/api/institution")
@CrossOrigin(origins = "http://localhost:8080")
public class InstitutionRestController {
    private final InstitutionService institutionService;

    public InstitutionRestController(InstitutionService institutionService) {
        this.institutionService = institutionService;
    }

    @GetMapping("/{id}")
    public Institution getDonationById(@PathVariable String id) {
        Integer institutionId = Integer.parseInt(id);
        return institutionService.findById(institutionId);
    }
}
