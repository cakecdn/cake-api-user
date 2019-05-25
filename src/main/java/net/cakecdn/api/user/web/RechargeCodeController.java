package net.cakecdn.api.user.web;

import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.RechargeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recharge-codes")
public class RechargeCodeController {

    private final RechargeCodeService rechargeCodeService;

    @Autowired
    public RechargeCodeController(RechargeCodeService rechargeCodeService) {
        this.rechargeCodeService = rechargeCodeService;
    }

    @PostMapping("/{rechargeCode}/users/{userId}")
    public AjaxResult useCode(@PathVariable String rechargeCode, @PathVariable Long userId) {
        boolean isSuccess = rechargeCodeService.useCode(userId, rechargeCode);
        return AjaxResult.whether(isSuccess);
    }
}
