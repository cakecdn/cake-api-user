package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.UserRemainingTraffic;
import net.cakecdn.api.user.entity.UserTrafficLog;
import net.cakecdn.api.user.repository.UserRemainingTrafficRepository;
import net.cakecdn.api.user.repository.UserTrafficLogRepository;
import net.cakecdn.api.user.service.UserTrafficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserTrafficServiceImpl implements UserTrafficService {

    private final UserRemainingTrafficRepository userRemainingTrafficRepository;
    private final UserTrafficLogRepository userTrafficLogRepository;

    @Autowired
    public UserTrafficServiceImpl(
            UserRemainingTrafficRepository userRemainingTrafficRepository,
            UserTrafficLogRepository userTrafficLogRepository
    ) {
        this.userRemainingTrafficRepository = userRemainingTrafficRepository;
        this.userTrafficLogRepository = userTrafficLogRepository;
    }

    @Override
    public UserRemainingTraffic getTraffics(Long userId) {
        Optional<UserRemainingTraffic> userProfileOpt = userRemainingTrafficRepository.findByUserId(userId);
        UserRemainingTraffic userRemainingTraffic = userProfileOpt.orElse(new UserRemainingTraffic(userId, 0L));
        if (!userProfileOpt.isPresent())
            userRemainingTrafficRepository.save(userRemainingTraffic);
        return userRemainingTraffic;
    }

    @Override
    public UserRemainingTraffic useTraffics(Long userId, Long using) {
        UserRemainingTraffic remainingTraffic = userRemainingTrafficRepository.findByUserId(userId).orElse(new UserRemainingTraffic(userId, 0L));
        remainingTraffic.setRemainingTrafficBytes(remainingTraffic.getRemainingTrafficBytes() - using);
        userRemainingTrafficRepository.save(remainingTraffic);
        return remainingTraffic;
    }

    @Override
    @Transactional
    public Map<Long, Long> useTraffics(Map<Long, Long> usingMap, String nodeName) {
        List<UserRemainingTraffic> userRemainingTraffics = userRemainingTrafficRepository.findAll();
        Map<Long, Long> userRemainingTrafficMap = UserRemainingTraffic.toMap(userRemainingTraffics);
        List<UserTrafficLog> trafficLogs = new ArrayList<>();

        for (Map.Entry<Long, Long> e : usingMap.entrySet()) {
            Long userId = e.getKey();
            Long using = e.getValue();
            Long newRemainingTraffic = userRemainingTrafficMap.getOrDefault(userId, 0L) - using;
            userRemainingTrafficMap.put(userId, newRemainingTraffic);
            trafficLogs.add(new UserTrafficLog(userId, using, nodeName));
        }

        List<UserRemainingTraffic> userRemainingTrafficList = UserRemainingTraffic.toList(userRemainingTrafficMap);
        userRemainingTrafficRepository.saveAll(userRemainingTrafficList);
        userTrafficLogRepository.saveAll(trafficLogs);

        return userRemainingTrafficMap;
    }
}
