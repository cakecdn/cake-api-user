package net.cakecdn.api.user.web;

import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.UserTrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-traffics")
public class UserTrafficController {

    private final UserTrafficService userTrafficService;

    @Autowired
    public UserTrafficController(UserTrafficService userTrafficService) {
        this.userTrafficService = userTrafficService;
    }

    @GetMapping
    public AjaxResult getTraffics(
            @RequestParam Long userId
    ) {
        return AjaxResult.whether(userTrafficService.getTraffics(userId));
    }
}
