package net.cakecdn.api.user.service.impl;

import net.cakecdn.api.user.entity.TrafficLogDo;
import net.cakecdn.api.user.entity.dto.HourRoutine;
import net.cakecdn.api.user.entity.dto.TrafficTrend;
import net.cakecdn.api.user.entity.dto.TrafficUnit;
import net.cakecdn.api.user.mapper.TrafficLogMapper;
import net.cakecdn.api.user.service.TrafficTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;

@Service
public class TrafficTrendServiceImpl implements TrafficTrendService {
    @Value("${cake.db.time-zone:0}")
    private int dbTimeZone;

    private final TrafficLogMapper trafficLogMapper;

    @Autowired
    public TrafficTrendServiceImpl(TrafficLogMapper trafficLogMapper) {
        this.trafficLogMapper = trafficLogMapper;
    }

    @Override
    public TrafficTrend trafficLogTrend(long userId) {
        List<TrafficLogDo> trafficLogDos = trafficLogMapper.hourly(userId);
        TrafficTrend trafficTrend = new TrafficTrend();
        long minBytes = trafficLogDos.size() == 0 ? 0 : trafficLogDos.get(0).getTrafficBytes();
        long maxBytes = minBytes;
        long total = 0;

        // 计算最大最小值和总字节数。
        for (TrafficLogDo trafficLogDo : trafficLogDos) {
            long trafficBytes = trafficLogDo.getTrafficBytes();
            minBytes = Long.min(minBytes, trafficBytes);
            maxBytes = Long.max(maxBytes, trafficBytes);
            total += trafficBytes;
        }

        // 横轴单位初始化，需要与数据库相同的时区时间。
        HourRoutine hourRoutine = trafficLogDos.size() == 0
                ? HourRoutine.hour(0)
                : HourRoutine.hour(trafficLogDos.get(0).getHour());
        TrafficUnit unit = TrafficUnit.getUnit(maxBytes); // 按照最大流量设置单位。

        // 设置横轴单位和图中数据。
        for (TrafficLogDo trafficLogDo : trafficLogDos) {
            int dataHour = trafficLogDo.getHour();        // 实际数据里的小时。可能比需要填充的横轴快，此情况发生时说明数据缺失，需要填充0。
            String label = localizedDate(hourRoutine.getHour()) + ":30"; // 横轴密铺为循环的横轴单位。

            // System.out.println(localizedDate(hourRoutine.getHour()));

            // 如果实际数据的小时数更快（与需标记横轴单位不相等）则填充0，直到相等为止。
            while (!hourRoutine.hourEqual(dataHour)) {
                minBytes = 0;                             // 一旦发生数据缺失，即设置最小数据为0。
                long trafficBytes = 0;


                trafficTrend.addLabel(label);
                trafficTrend.addSeries(unit.getTraffic(trafficBytes));
                hourRoutine = hourRoutine.hourIncrease();       // 横轴单位额外流动一次。
                label = localizedDate(hourRoutine.getHour()) + ":30";    // 更新横轴单位。
            }

            long trafficBytes = trafficLogDo.getTrafficBytes();
            trafficTrend.addLabel(label);
            trafficTrend.addSeries(unit.getTraffic(trafficBytes));
            hourRoutine = hourRoutine.hourIncrease();           // 横轴单位继续流动。
        }

        trafficTrend.setTrafficUnit(unit);                // 设置单位。
        trafficTrend.setTotalBytes(total);                // 设置总字节数。
        trafficTrend.setMin(unit.getTraffic(minBytes));   // 设置最小大小。
        trafficTrend.setMax(unit.getTraffic(maxBytes));   // 设置最大大小。
        return trafficTrend;
    }

    private int localizedDate(int hour) {

        int resourceOffset = dbTimeZone;
        int targetOffset = new GregorianCalendar().getTimeZone().getOffset(System.currentTimeMillis()) / 1000 / 60 / 60;

        if (hour - resourceOffset < 0) {
            hour += 24 - resourceOffset;
        } else {
            hour -= resourceOffset;
        }

        if (hour + targetOffset > 24) {
            hour += targetOffset - 24;
        } else {
            hour += targetOffset;
        }

        return hour;
    }
}
