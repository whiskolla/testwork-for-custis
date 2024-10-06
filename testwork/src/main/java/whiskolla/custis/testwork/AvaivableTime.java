package whiskolla.custis.testwork;

import java.time.LocalTime;
import java.time.ZoneId;

public class AvaivableTime {
    private LocalTime startTime;
    private LocalTime endTime;

    public AvaivableTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isAvaivableTime() {
        LocalTime currentTime = LocalTime.now(ZoneId.systemDefault());
        return !currentTime.isBefore(startTime) && !currentTime.isAfter(endTime);
    }
}
