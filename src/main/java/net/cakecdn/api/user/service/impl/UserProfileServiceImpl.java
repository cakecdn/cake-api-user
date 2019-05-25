package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.UserProfile;
import net.cakecdn.api.user.repository.UserProfileRepository;
import net.cakecdn.api.user.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserProfileServiceImpl(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public long getTrafficBytes(Long userId) {
        Optional<UserProfile> userProfileOpt = userProfileRepository.findByUserId(userId);

        return userProfileOpt.orElse(new UserProfile(userId, 0L)).getTotalTrafficBytes();
    }

    public long updateTraffics(Long userId, Long trafficBytes) {
        Optional<UserProfile> userProfileOpt = userProfileRepository.findByUserId(userId);

        UserProfile userProfile = userProfileOpt.orElse(new UserProfile(userId, trafficBytes));
        userProfile.setTotalTrafficBytes(trafficBytes);

        return userProfile.getTotalTrafficBytes();
    }

    @Override
    public long reduceTraffics(Long userId, Long trafficBytesReduce) {
        Optional<UserProfile> userProfileOpt = userProfileRepository.findByUserId(userId);

        UserProfile userProfile = userProfileOpt.orElse(new UserProfile(userId, 0L));
        userProfile.setTotalTrafficBytes(userProfile.getTotalTrafficBytes() - trafficBytesReduce);

        return userProfile.getTotalTrafficBytes();
    }

}
