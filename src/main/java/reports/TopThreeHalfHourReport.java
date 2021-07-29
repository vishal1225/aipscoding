package reports;

import report.processors.FileData;

import java.util.List;

public class TopThreeHalfHourReport implements IReport{
    List<FileData> fileDataList;
    public TopThreeHalfHourReport(List<FileData> fileDataList) {
        this.fileDataList = fileDataList;
    }

    @Override
    public void getData() {
        System.out.println("Top three half hours of highest traffic are.");
        fileDataList.subList(0,3).forEach(fileData -> {
            System.out.println(fileData.getTimeStamp());
        });
    }
}
