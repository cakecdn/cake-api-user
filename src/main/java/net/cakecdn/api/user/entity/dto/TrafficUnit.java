package net.cakecdn.api.user.entity.dto;

public enum TrafficUnit {
    B, KB, MB, GB, TB, PB, EB;

    public static TrafficUnit getUnit(double trafficBytes) {
        TrafficUnit unit = B;
        if (trafficBytes > 1024L) unit = KB;
        if (trafficBytes > 1024L * 1024) unit = MB;
        if (trafficBytes > 1024L * 1024 * 1024) unit = GB;
        if (trafficBytes > 1024L * 1024 * 1024 * 1024) unit = TB;
        if (trafficBytes > 1024L * 1024 * 1024 * 1024 * 1024) unit = PB;
        if (trafficBytes > 1024L * 1024 * 1024 * 1024 * 1024 * 1024) unit = EB;
        return unit;
    }

    public double getTraffic(long trafficBytes) {
        double traffic = trafficBytes;
        if (this == KB)
            traffic = trafficBytes / 1024.0;
        if (this == MB)
            traffic = trafficBytes / 1024.0 / 1024;
        if (this == GB)
            traffic = trafficBytes / 1024.0 / 1024 / 1024;
        if (this == TB)
            traffic = trafficBytes / 1024.0 / 1024 / 1024 / 1024;
        if (this == PB)
            traffic = trafficBytes / 1024.0 / 1024 / 1024 / 1024 / 1024;
        if (this == EB)
            traffic = trafficBytes / 1024.0 / 1024 / 1024 / 1024 / 1024 / 1024;
        return traffic;
    }
}
