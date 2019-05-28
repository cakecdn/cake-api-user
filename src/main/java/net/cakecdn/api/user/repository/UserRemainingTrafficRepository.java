package net.cakecdn.api.user.repository;

import net.cakecdn.api.user.entity.UserRemainingTraffic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRemainingTrafficRepository extends JpaRepository<UserRemainingTraffic, Long> {
    Optional<UserRemainingTraffic> findByUserId(Long userId);
}
