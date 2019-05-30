package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.UserRemainingTraffic;
import net.cakecdn.api.user.repository.UserRemainingTrafficRepository;
import net.cakecdn.api.user.service.UserTrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserTrafficServiceImpl implements UserTrafficService {

    private final UserRemainingTrafficRepository userRemainingTrafficRepository;

    @Autowired
    public UserTrafficServiceImpl(
            UserRemainingTrafficRepository userRemainingTrafficRepository
    ) {
        this.userRemainingTrafficRepository = userRemainingTrafficRepository;
    }

    @Override
    public UserRemainingTraffic getTraffics(Long userId) {
        Optional<UserRemainingTraffic> userProfileOpt = userRemainingTrafficRepository.findByUserId(userId);
        UserRemainingTraffic userRemainingTraffic = userProfileOpt.orElse(new UserRemainingTraffic(userId, 0L));
        if (!userProfileOpt.isPresent())
            userRemainingTrafficRepository.save(userRemainingTraffic);
        return userRemainingTraffic;
    }
}
