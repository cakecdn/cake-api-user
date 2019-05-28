package net.cakecdn.api.user.service;

import net.cakecdn.api.user.entity.UserRemainingTraffic;

import java.util.Map;

public interface UserTrafficService {
    Map<Long, Long> useTraffics(Map<Long, Long> usingMap);

    UserRemainingTraffic getTraffics(Long userId);

    UserRemainingTraffic useTraffics(Long userId, Long using);
}
