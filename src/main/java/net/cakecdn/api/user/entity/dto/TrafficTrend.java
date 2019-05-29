package net.cakecdn.api.user.entity.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class TrafficTrend {
    private double min;
    private double max;
    private List<String> labels;
    private List<Double> series;
    private TrafficUnit trafficUnit;
    private long totalBytes;

    public TrafficTrend() {
        this.min = 0;
        this.max = 1000;
        this.labels = new ArrayList<>();
        this.series = new ArrayList<>();
        this.trafficUnit = TrafficUnit.B;
        this.totalBytes = 0;
    }

    public void addLabel(String time) {
        this.labels.add(time);
    }

    public void addSeries(double series) {
        this.series.add(series);
    }
}
