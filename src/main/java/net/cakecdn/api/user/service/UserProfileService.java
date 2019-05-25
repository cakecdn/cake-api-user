package net.cakecdn.api.user.service;

public interface UserProfileService {
    long getTrafficBytes(Long userId);

    long updateTraffics(Long userId, Long trafficBytes);

    long reduceTraffics(Long userId, Long trafficBytesReduce);
}
