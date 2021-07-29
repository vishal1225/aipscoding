package reports;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class NumberOfCarsPerDayReport implements IReport {
    Map<LocalDate, Integer> numberOfCarsPerDayMap;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public NumberOfCarsPerDayReport(Map<LocalDate, Integer> numberOfCarsPerDayMap) {
        this.numberOfCarsPerDayMap = numberOfCarsPerDayMap;
    }

    @Override
    public void getData() {

        this.numberOfCarsPerDayMap.entrySet().stream().forEach(entry -> {
            System.out.println( entry.getKey().format(formatter).concat(": ").concat(String.valueOf(entry.getValue())));
        });
    }
}
