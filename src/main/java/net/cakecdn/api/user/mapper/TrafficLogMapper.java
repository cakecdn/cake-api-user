package net.cakecdn.api.user.mapper;

import net.cakecdn.api.user.entity.TrafficLogDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("trafficLogMapper")
public interface TrafficLogMapper {

    @Select("select extract(year from log_time)  year,\n" +
            "       extract(month from log_time) month,\n" +
            "       extract(day from log_time)   day,\n" +
            "       extract(hour from log_time)  hour,\n" +
            "       sum(traffic_bytes)           trafficBytes\n" +
            "from traffic_log\n" +
            "where user_id = #{userId}\n" +
            "  and log_time between DATE_SUB(now(), interval 1 day) and now()\n" +
            "group by extract(year from log_time),\n" +
            "         extract(month from log_time),\n" +
            "         extract(day from log_time),\n" +
            "         extract(hour from log_time);")
    List<TrafficLogDo> hourly(@Param("userId") long userId);
}
