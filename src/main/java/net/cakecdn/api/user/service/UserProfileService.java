package net.cakecdn.api.user.service;

import net.cakecdn.api.user.entity.UserProfile;

public interface UserProfileService {
    UserProfile getUserProfile(Long userId);
}
