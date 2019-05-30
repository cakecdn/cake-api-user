package net.cakecdn.api.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrafficLogDo {
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Long trafficBytes;
}
