package report.generators;

import report.processors.FileData;
import reports.IReport;
import reports.NumberOfCarsPerDayReport;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NumberOfVehiclesEachDayReportGenerator implements IReportGenerators {
    private List<FileData> fileDataList;
    public NumberOfVehiclesEachDayReportGenerator(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    @Override
    public IReport generateReport() {
        Map<LocalDate, Integer> numberOfCarsPerDayMap = fileDataList.parallelStream().collect(Collectors.groupingBy( FileData:: getLocalDate, Collectors.summingInt(FileData::getNumberOfCars)));
        return new NumberOfCarsPerDayReport(numberOfCarsPerDayMap);
    }
}
