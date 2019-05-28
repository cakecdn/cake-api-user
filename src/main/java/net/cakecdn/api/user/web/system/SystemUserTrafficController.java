package net.cakecdn.api.user.web.system;

import net.cakecdn.api.user.entity.UserRemainingTraffic;
import net.cakecdn.api.user.service.UserTrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/system/user-traffics")
public class SystemUserTrafficController {

    private final UserTrafficService userTrafficService;

    @Autowired
    public SystemUserTrafficController(UserTrafficService userTrafficService) {
        this.userTrafficService = userTrafficService;
    }

    @GetMapping
    public UserRemainingTraffic getTraffics(
            @RequestParam Long userId
    ) {
        return userTrafficService.getTraffics(userId);
    }

    @PostMapping
    public Map<Long, Long> useTraffics(
            @RequestBody Map<Long, Long> usedTrafficMap
    ) {
        return userTrafficService.useTraffics(usedTrafficMap);
    }

    @PostMapping("/exchange")
    public UserRemainingTraffic useTraffics(
            @RequestParam Long userId,
            @RequestBody Long trafficBytes
    ) {
        return userTrafficService.useTraffics(userId, trafficBytes);
    }
}
