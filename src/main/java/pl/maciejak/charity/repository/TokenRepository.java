package pl.maciejak.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.maciejak.charity.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
}