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

    @GetMapping
    public AjaxResult getProfile(@RequestParam Long userId) {
        return AjaxResult.success(userProfileService.getUserProfile(userId));
    }
}
