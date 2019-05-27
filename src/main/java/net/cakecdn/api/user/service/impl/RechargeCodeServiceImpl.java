package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.RechargeCode;
import net.cakecdn.api.user.entity.UserProfile;
import net.cakecdn.api.user.repository.RechargeCodeRepository;
import net.cakecdn.api.user.repository.UserProfileRepository;
import net.cakecdn.api.user.service.RechargeCodeService;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RechargeCodeServiceImpl implements RechargeCodeService {

    private final RechargeCodeRepository rechargeCodeRepository;
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public RechargeCodeServiceImpl(
            RechargeCodeRepository rechargeCodeRepository,
            UserProfileRepository userProfileRepository
    ) {
        this.rechargeCodeRepository = rechargeCodeRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public RechargeCode getCode(String code) {
        Optional<RechargeCode> rechargeCodeOpt = rechargeCodeRepository.findByCode(code);
        return rechargeCodeOpt.orElse(null);
    }

    @Override
    public boolean useCode(Long userId, String code) {
        Optional<RechargeCode> rechargeCodeOpt = rechargeCodeRepository.findByCode(code);
        Optional<UserProfile> userProfileOpt = userProfileRepository.findByUserId(userId);

        if (rechargeCodeOpt.isPresent() && rechargeCodeOpt.get().getUserId() == null) {
            UserProfile defaultUserProfile = new UserProfile(userId, 0L, 0L);
            UserProfile userProfile = userProfileOpt.orElse(defaultUserProfile);
            RechargeCode rechargeCode = rechargeCodeOpt.get();
            long originalUserTrafficBytes = userProfile.getUnusedTrafficBytes();

            Date now = new Date();
            if (rechargeCode.getExpire().compareTo(now) < 0) return false;

            rechargeCode.setUserId(userId);
            userProfile.setUnusedTrafficBytes(rechargeCode.getTrafficBytes() + originalUserTrafficBytes); // 用户充值码移交
            rechargeCodeRepository.save(rechargeCode);    // 标定充值码已用
            userProfileRepository.save(userProfile);      // 保存用户新流量

            return true;
        }

        return false;
    }
}
