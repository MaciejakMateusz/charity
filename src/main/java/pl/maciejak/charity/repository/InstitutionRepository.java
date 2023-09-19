package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejak.charity.entity.Institution;

public interface InstitutionRepository extends JpaRepository<Institution, Integer> {
}
