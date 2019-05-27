package net.cakecdn.api.user.web;

import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.UserProfileService;
import net.cakecdn.api.user.service.UserProfileTrafficsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traffics")
public class UserProfileTrafficsController {

    private final UserProfileTrafficsService userProfileTrafficsService;

    @Autowired
    public UserProfileTrafficsController(UserProfileTrafficsService userProfileTrafficsService) {
        this.userProfileTrafficsService = userProfileTrafficsService;
    }

    @PostMapping("/use")
    public AjaxResult useTraffics(
            @RequestParam Long userId,
            @RequestBody Long trafficBytesReduce
    ) {
        return AjaxResult.success(userProfileTrafficsService.useTraffics(userId, trafficBytesReduce));
    }
}
