package net.cakecdn.api.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrafficLog {
    private String year;
    private String month;
    private String day;
    private String hour;
    private Long trafficBytes;
}
