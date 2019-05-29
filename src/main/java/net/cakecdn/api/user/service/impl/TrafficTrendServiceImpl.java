package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.TrafficLog;
import net.cakecdn.api.user.entity.dto.TrafficTrend;
import net.cakecdn.api.user.entity.dto.TrafficUnit;
import net.cakecdn.api.user.mapper.TrafficLogMapper;
import net.cakecdn.api.user.service.TrafficTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrafficTrendServiceImpl implements TrafficTrendService {
    private final TrafficLogMapper trafficLogMapper;

    @Autowired
    public TrafficTrendServiceImpl(TrafficLogMapper trafficLogMapper) {
        this.trafficLogMapper = trafficLogMapper;
    }

    @Override
    public TrafficTrend trafficLogTrend(long userId) {
        List<TrafficLog> trafficLogs = trafficLogMapper.hourly(userId);
        TrafficTrend trafficTrend = new TrafficTrend();
        long minBytes = trafficLogs.get(0).getTrafficBytes();
        long maxBytes = minBytes;
        long total = 0;
        for (TrafficLog trafficLog : trafficLogs) {
            long trafficBytes = trafficLog.getTrafficBytes();
            minBytes = Long.min(minBytes, trafficBytes);
            maxBytes = Long.max(maxBytes, trafficBytes);
            total += trafficBytes;
        }
        TrafficUnit unit = TrafficUnit.getUnit(maxBytes); // 按照最大流量设置单位
        for (TrafficLog trafficLog : trafficLogs) {
            String label = trafficLog.getHour() + ":00";
            long trafficBytes = trafficLog.getTrafficBytes();
            trafficTrend.addLabel(label);
            trafficTrend.addSeries(unit.getTraffic(trafficBytes));
        }
        trafficTrend.setTrafficUnit(unit);                // 设置单位
        trafficTrend.setTotalBytes(total);                // 设置总字节数
        trafficTrend.setMin(unit.getTraffic(minBytes));   // 设置最小大小
        trafficTrend.setMax(unit.getTraffic(maxBytes));   // 设置最大大小
        return trafficTrend;
    }
}
