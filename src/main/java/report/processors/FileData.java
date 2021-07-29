package report.processors;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FileData {
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    private LocalDateTime timeStamp;

    public int getNumberOfCars() {
        return numberOfCars;
    }

    private int numberOfCars;

    public FileData(LocalDateTime timeStamp, int numberOfCars) {
        this.timeStamp = timeStamp;
        this.numberOfCars = numberOfCars;
    }

    public LocalDate getLocalDate() {
        return this.timeStamp.toLocalDate();
    }
}
