package net.cakecdn.api.user.service;

import net.cakecdn.api.user.entity.UserProfile;

public interface UserProfileTrafficsService {
    UserProfile useTraffics(Long userId, Long usedTrafficBytes);
}
