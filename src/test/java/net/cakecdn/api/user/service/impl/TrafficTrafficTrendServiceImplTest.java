package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.CakeApiUserApplicationTests;
import net.cakecdn.api.user.entity.dto.TrafficTrend;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TrafficTrafficTrendServiceImplTest extends CakeApiUserApplicationTests {
    @Autowired
    TrafficTrendServiceImpl trafficLogService;

    @Test
    public void trafficLogTrend() {
        TrafficTrend trafficTrend = trafficLogService.trafficLogTrend(4L);
        System.out.println(trafficTrend);
    }
}