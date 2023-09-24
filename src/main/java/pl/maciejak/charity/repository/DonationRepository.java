package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.entity.User;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    long sumBags();

    List<Donation> findDonationsByUser(User user);
}
