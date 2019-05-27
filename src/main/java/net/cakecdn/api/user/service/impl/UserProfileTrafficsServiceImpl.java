package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.UserProfile;
import net.cakecdn.api.user.repository.UserProfileRepository;
import net.cakecdn.api.user.service.UserProfileService;
import net.cakecdn.api.user.service.UserProfileTrafficsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileTrafficsServiceImpl implements UserProfileTrafficsService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileTrafficsServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserProfile useTraffics(Long userId, Long usedTrafficBytes) {
        Optional<UserProfile> userProfileOpt = userProfileRepository.findByUserId(userId);
        UserProfile userProfile = userProfileOpt.orElse(new UserProfile(userId, 0L, 0L));
        userProfile.setUnusedTrafficBytes(userProfile.getUnusedTrafficBytes() - usedTrafficBytes);
        userProfile.setUsedTrafficBytes(userProfile.getUsedTrafficBytes() + usedTrafficBytes);
        return userProfile;
    }
}
