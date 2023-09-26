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
    public Donation findById(Long id) {
        return donationRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public List<Donation> findAllByUserAndArchived(User user, boolean isArchived) {
        return donationRepository.findDonationsByUserAndArchived(user, isArchived);
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
    public void updateStatus(Donation donation) {
        Donation existingDonation = findById(donation.getId());

        existingDonation.setPickedUp(true);
        existingDonation.setPickedUpDate(LocalDate.now());
        existingDonation.setPickedUpTime(LocalTime.now());
        save(existingDonation);
    }

    @Override
    public void archive(Donation donation) {
        Donation existingDonation = findById(donation.getId());
        existingDonation.setArchived(true);
        save(existingDonation);
    }

    @Override
    public Long donationsCount() {
        return donationRepository.count();
    }

    @Override
    public Long bagsCount() {
        return donationRepository.sumBags().orElse(0L);
    }
}
