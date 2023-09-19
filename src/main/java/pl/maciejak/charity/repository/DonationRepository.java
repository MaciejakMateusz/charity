package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejak.charity.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
}
