package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.maciejak.charity.entity.DonationArchive;

public interface DonationArchiveRepository extends JpaRepository<DonationArchive, Long> {
}
