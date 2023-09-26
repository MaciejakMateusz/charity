package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.entity.User;

import java.security.Principal;
import java.util.List;

public interface DonationServiceInterface {
    Donation findById(Long id);

    List<Donation> findAllByUserAndArchived(User user, boolean isArchived);

    void save(Donation donation, Principal principal);

    void updateStatus(Donation donation);

    void archive(Donation donation);

    Long donationsCount();

    Long bagsCount();
}
