package net.cakecdn.api.user.service;

public interface RechargeCodeService {
    boolean useCode(Long userId, String code);
}
