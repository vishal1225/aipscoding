package reports;

import report.processors.FileData;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class ContinuousLeastHalfHourReport implements IReport {
    public List<FileData> getLeastLocalDateTime() {
        return leastLocalDateTime;
    }

    private List<FileData> leastLocalDateTime;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public ContinuousLeastHalfHourReport(List<FileData> leastLocalDateTime) {
        this.leastLocalDateTime = leastLocalDateTime;
    }

    @Override
    public void getData() {
        System.out.println("1.5 hour duration with least cars is :");
        this.leastLocalDateTime.forEach(fileData -> {
            System.out.println(fileData.getTimeStamp().format(formatter).concat(": ").concat(String.valueOf(fileData.getNumberOfCars())));
                }

        );
    }
}
