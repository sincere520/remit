package dev.practice.remit.infrastructure.remit;

import dev.practice.remit.domain.remit.Remit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RemitRepository extends JpaRepository<Remit, Long> {
    Optional<Remit> findByRemitToken(String remitToken);
    Optional<Remit> findByOrderToken(String orderToken);
}
