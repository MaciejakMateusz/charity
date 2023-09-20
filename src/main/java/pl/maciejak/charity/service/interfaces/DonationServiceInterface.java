package pl.maciejak.charity.service.interfaces;

import pl.maciejak.charity.entity.Donation;

import java.util.List;

public interface DonationServiceInterface {
    Donation findById(Integer id);

    List<Donation> findAll();

    void save(Donation donation);

    void delete(Donation donation);

    Integer donationsCount();
}
