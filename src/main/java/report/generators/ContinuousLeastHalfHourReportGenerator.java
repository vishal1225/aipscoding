package report.generators;

import report.processors.FileData;
import reports.ContinuousLeastHalfHourReport;
import reports.IReport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ContinuousLeastHalfHourReportGenerator implements IReportGenerators {
    private List<FileData> fileDataList;
    Map<LocalDate, Integer> numberOfCarsPerDayMap;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ContinuousLeastHalfHourReportGenerator(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    @Override
    public IReport generateReport() {

        Map<LocalDate, List<FileData>> numberOfCarsPerDayMap = this.fileDataList.parallelStream().collect(Collectors.groupingBy( FileData:: getLocalDate));

        final int[] leastNumberOfCars = {0};
        final FileData[] leastLocalDateTimeList = {null, null, null};
        numberOfCarsPerDayMap.entrySet().stream().forEach(entry ->{
            List<FileData> perDayFileData = entry.getValue();
            perDayFileData.sort(Comparator.comparing(FileData::getTimeStamp));

            for(int index = 0; index < perDayFileData.size() - 3; index++) {
                int numberOfCars = 0;
                for (int newIndex = index ; newIndex < index + 3 && newIndex < perDayFileData.size(); newIndex++) {
                    numberOfCars += perDayFileData.get(newIndex).getNumberOfCars();
                }
                if(leastNumberOfCars[0] > numberOfCars || leastNumberOfCars[0] == 0 && perDayFileData.get(index) != null
                        && perDayFileData.get(index + 1 ) != null
                && perDayFileData.get(index + 2 ) != null) {
                    leastNumberOfCars[0] = numberOfCars;
                    leastLocalDateTimeList[0] = perDayFileData.get(index);
                    leastLocalDateTimeList[1] = perDayFileData.get(index + 1 );
                    leastLocalDateTimeList[2] = perDayFileData.get(index + 2);
                }
            }
        });

        return new ContinuousLeastHalfHourReport(Arrays.asList(leastLocalDateTimeList));
    }
}
