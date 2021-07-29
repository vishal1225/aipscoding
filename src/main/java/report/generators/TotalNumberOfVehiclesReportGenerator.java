package report.generators;

import report.processors.FileData;
import reports.IReport;
import reports.TotalNumberOfVehiclesReport;

import java.util.List;

public class TotalNumberOfVehiclesReportGenerator implements IReportGenerators {
    private final List<FileData> fileDataList;

    public TotalNumberOfVehiclesReportGenerator(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    @Override
    public IReport generateReport() {
       return new TotalNumberOfVehiclesReport(this.fileDataList.stream().filter(fileData -> fileData.getNumberOfCars() > 0).mapToInt(FileData::getNumberOfCars).sum());
    }
}
