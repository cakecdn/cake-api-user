package net.cakecdn.api.user.web;

import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.TrafficTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/traffic-trend")
public class TrafficTrendController {
    private final TrafficTrendService trafficTrendService;

    @Autowired
    public TrafficTrendController(TrafficTrendService trafficTrendService) {
        this.trafficTrendService = trafficTrendService;
    }

    @GetMapping("/users/{userId}")
    public AjaxResult getTrafficTrend(@PathVariable long userId) {
        return AjaxResult.success(trafficTrendService.trafficLogTrend(userId));
    }
}
