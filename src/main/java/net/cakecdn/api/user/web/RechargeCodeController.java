package net.cakecdn.api.user.web;

import net.cakecdn.api.user.entity.RechargeCode;
import net.cakecdn.api.user.entity.dto.AjaxResult;
import net.cakecdn.api.user.service.RechargeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recharge-codes")
public class RechargeCodeController {

    private final RechargeCodeService rechargeCodeService;

    @Autowired
    public RechargeCodeController(RechargeCodeService rechargeCodeService) {
        this.rechargeCodeService = rechargeCodeService;
    }

    @GetMapping("/{rechargeCode}")
    public AjaxResult getCode(@PathVariable String rechargeCode) {
        RechargeCode recharge =  rechargeCodeService.getCode(rechargeCode);
        return AjaxResult.whether(recharge);
    }

    @PostMapping("/{rechargeCode}")
    public AjaxResult useCode(@PathVariable String rechargeCode, @RequestParam Long userId) {
        boolean isSuccess = rechargeCodeService.useCode(userId, rechargeCode);
        return AjaxResult.whether(isSuccess);
    }
}
