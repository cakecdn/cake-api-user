package net.cakecdn.api.user.web;

import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/users/{userId}/traffic")
    public AjaxResult traffic(
            @PathVariable Long userId,
            @RequestBody Long trafficBytes
    ) {
        return AjaxResult.success(userProfileService.updateTraffics(userId, trafficBytes));
    }

    @PostMapping("/users/{userId}/traffic-reduce")
    public AjaxResult trafficReduce(
            @PathVariable Long userId,
            @RequestBody Long trafficBytesReduce
    ) {
        return AjaxResult.success(userProfileService.reduceTraffics(userId, trafficBytesReduce));
    }
}
