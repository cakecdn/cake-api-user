package net.cakecdn.api.user.mapper;

import net.cakecdn.api.user.CakeApiUserApplicationTests;
import net.cakecdn.api.user.entity.TrafficLog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TrafficLogMapperTest extends CakeApiUserApplicationTests {

    @Autowired
    private TrafficLogMapper trafficLogMapper;

    @Test
    public void hourly() {
        List<TrafficLog> ts = trafficLogMapper.hourly(4L);
        System.out.println(ts);
    }
}