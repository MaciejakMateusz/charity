package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.maciejak.charity.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    @Query(value = "SELECT COUNT(*) FROM charity_donation.dontations", nativeQuery = true)
    Integer countAll();
}
