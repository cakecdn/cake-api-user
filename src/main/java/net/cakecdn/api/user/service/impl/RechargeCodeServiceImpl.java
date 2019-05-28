package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.RechargeCode;
import net.cakecdn.api.user.entity.UserRemainingTraffic;
import net.cakecdn.api.user.repository.RechargeCodeRepository;
import net.cakecdn.api.user.repository.UserRemainingTrafficRepository;
import net.cakecdn.api.user.service.RechargeCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RechargeCodeServiceImpl implements RechargeCodeService {

    private final RechargeCodeRepository rechargeCodeRepository;
    private final UserRemainingTrafficRepository userRemainingTrafficRepository;

    @Autowired
    public RechargeCodeServiceImpl(
            RechargeCodeRepository rechargeCodeRepository,
            UserRemainingTrafficRepository userRemainingTrafficRepository
    ) {
        this.rechargeCodeRepository = rechargeCodeRepository;
        this.userRemainingTrafficRepository = userRemainingTrafficRepository;
    }

    @Override
    public RechargeCode getCode(String code) {
        Optional<RechargeCode> rechargeCodeOpt = rechargeCodeRepository.findByCode(code);
        return rechargeCodeOpt.orElse(null);
    }

    @Override
    public boolean useCode(Long userId, String code) {
        Optional<RechargeCode> rechargeCodeOpt = rechargeCodeRepository.findByCode(code);
        Optional<UserRemainingTraffic> userProfileOpt = userRemainingTrafficRepository.findByUserId(userId);

        if (rechargeCodeOpt.isPresent() && rechargeCodeOpt.get().getUserId() == null) {
            UserRemainingTraffic defaultUserRemainingTraffic = new UserRemainingTraffic(userId, 0L);
            UserRemainingTraffic userRemainingTraffic = userProfileOpt.orElse(defaultUserRemainingTraffic);
            RechargeCode rechargeCode = rechargeCodeOpt.get();
            long originalUserTrafficBytes = userRemainingTraffic.getRemainingTrafficBytes();

            Date now = new Date();
            if (rechargeCode.getExpire().compareTo(now) < 0) return false;

            rechargeCode.setUserId(userId);
            userRemainingTraffic.setRemainingTrafficBytes(rechargeCode.getTrafficBytes() + originalUserTrafficBytes); // 用户充值码移交
            rechargeCodeRepository.save(rechargeCode);    // 标定充值码已用
            userRemainingTrafficRepository.save(userRemainingTraffic);      // 保存用户新流量

            return true;
        }

        return false;
    }
}
