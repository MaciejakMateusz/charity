package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.entity.User;

import java.util.List;

public interface DonationServiceInterface {
    Donation findById(Long id);

    List<Donation> findAll();

    void save(Donation donation);

    void updateStatus(Donation donation);

    void archive(Donation donation);

    long donationsCount();

    long bagsCount();

    List<Donation> findAllByUser(User user);
}
