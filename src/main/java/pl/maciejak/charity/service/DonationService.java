package pl.maciejak.charity.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import pl.maciejak.charity.entity.Donation;
import pl.maciejak.charity.entity.User;
import pl.maciejak.charity.repository.DonationRepository;
import pl.maciejak.charity.service.interfaces.DonationServiceInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class DonationService implements DonationServiceInterface {

    private final DonationRepository donationRepository;
    private final UserService userService;

    public DonationService(DonationRepository donationRepository, UserService userService) {
        this.donationRepository = donationRepository;
        this.userService = userService;
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
    public List<Donation> findAllByUser(User user) {
        return donationRepository.findDonationsByUser(user);
    }

    @Override
    public void save(Donation donation) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);

        donation.setUser(user);

        donationRepository.save(donation);
    }

    @Override
    public void updateStatus(long id) {
        Donation donation = findById((int) id);
        donation.setPickedUp(true);
        donation.setPickedUpDate(LocalDate.now());
        donation.setPickUpTime(LocalTime.now());
        save(donation);
    }

    @Override
    public void delete(Donation donation) {
        donationRepository.delete(donation);
    }

    @Override
    public long donationsCount() {
        return donationRepository.count();
    }

    @Override
    public long bagsCount() {
        return donationRepository.sumBags();
    }
}
