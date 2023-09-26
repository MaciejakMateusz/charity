package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.entity.User;

import java.util.List;
import java.util.Optional;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Optional<Long> sumBags();

    @Query("SELECT d FROM Donation d " +
            "WHERE d.user = :user AND d.isArchived = :isArchived " +
            "ORDER BY " +
            "CASE WHEN d.isPickedUp = true THEN 1 ELSE 2 END," +
            "CASE WHEN d.isPickedUp = false THEN d.pickUpDate END," +
            "d.created")
    List<Donation> findDonationsByUserAndArchived(User user, boolean isArchived);
}