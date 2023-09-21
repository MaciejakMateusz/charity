package pl.maciejak.charity.service;

import org.springframework.stereotype.Service;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.repository.DonationRepository;
import pl.maciejak.charity.service.interfaces.DonationServiceInterface;

import java.util.List;

@Service
public class DonationService implements DonationServiceInterface {

    private final DonationRepository donationRepository;

    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public Donation findById(Integer id) {
        return donationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public void delete(Donation donation) {
        donationRepository.delete(donation);
    }

    @Override
    public Integer donationsCount() {
        return donationRepository.countAll();
    }

    @Override
    public Integer bagsCount() {
        List<Donation> allDonations = donationRepository.findAll();
        int sum = 0;
        for(Donation donation : allDonations) {
            sum += donation.getQuantity();
        }
        return sum;
    }
}
