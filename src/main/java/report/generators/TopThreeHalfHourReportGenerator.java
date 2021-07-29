package report.generators;

import report.processors.FileData;
import reports.IReport;
import reports.TopThreeHalfHourReport;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TopThreeHalfHourReportGenerator implements IReportGenerators {
    List<FileData> fileDataList;
    public TopThreeHalfHourReportGenerator(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    @Override
    public IReport generateReport() {
        this.fileDataList.sort(Comparator.comparing(FileData::getNumberOfCars));
        Collections.reverse(this.fileDataList);
        return new TopThreeHalfHourReport(this.fileDataList) ;
    }
}
