package net.cakecdn.api.user.service;

import net.cakecdn.api.user.entity.RechargeCode;

public interface RechargeCodeService {
    RechargeCode getCode(String code);

    boolean useCode(Long userId, String code);
}
