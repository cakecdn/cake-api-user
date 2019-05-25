package net.cakecdn.api.user.repository;

import net.cakecdn.api.user.entity.RechargeCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RechargeCodeRepository extends JpaRepository<RechargeCode, Long> {
    Optional<RechargeCode> findByCode(String code);
}
