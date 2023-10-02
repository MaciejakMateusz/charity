package pl.maciejak.charity.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.maciejak.charity.entity.Institution;
import pl.maciejak.charity.repository.InstitutionRepository;
import pl.maciejak.charity.service.interfaces.InstitutionServiceInterface;

import java.util.List;

@Service
public class InstitutionService implements InstitutionServiceInterface {

    private final InstitutionRepository institutionRepository;

    public InstitutionService(InstitutionRepository institutionRepository) {
        this.institutionRepository = institutionRepository;
    }

    @Override
    public Institution findById(Long id) {
        return institutionRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Institution> findAll(Pageable pageable) {
        return institutionRepository.findAll(pageable);
    }

    @Override
    public Page<Institution> findByPartialEmail(String email, Pageable pageable) {
        return institutionRepository.findInstitutionByEmailContains(email, pageable);
    }

    @Override
    public List<Institution> findAll() {
        return institutionRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {
        return institutionRepository.existsById(id);
    }


    @Override
    public void save(Institution institution) {
        institutionRepository.save(institution);
    }

    @Override
    public void delete(Institution institution) {
        institutionRepository.delete(institution);
    }
}
