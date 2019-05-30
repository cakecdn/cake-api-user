package net.cakecdn.api.user.web.system;

import net.cakecdn.api.user.entity.UserRemainingTraffic;
import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.UserTrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
