package net.cakecdn.api.user.repository;

import net.cakecdn.api.user.entity.UserRemainingTraffic;
import net.cakecdn.api.user.entity.UserTrafficLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserTrafficLogRepository extends JpaRepository<UserTrafficLog, Long> {
    Optional<UserTrafficLog> findByUserId(Long userId);
}
