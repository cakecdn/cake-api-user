package net.cakecdn.api.user.service;

import net.cakecdn.api.user.entity.UserRemainingTraffic;

public interface UserTrafficService {
    UserRemainingTraffic getTraffics(Long userId);
}
