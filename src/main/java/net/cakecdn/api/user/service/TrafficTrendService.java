package net.cakecdn.api.user.service;

import net.cakecdn.api.user.entity.dto.TrafficTrend;

public interface TrafficTrendService {
    TrafficTrend trafficLogTrend(long userId);
}
