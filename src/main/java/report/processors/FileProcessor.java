package report.processors;

import report.generators.*;
import reports.IReport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class FileProcessor {
    private static Logger logger = Logger.getLogger(FileProcessor.class.getName());

    private List<FileData> fileDataList = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    public void readFile(String filePath) {
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(filePath).toURI()))) {
            stream.forEach(record -> {
                fileDataList.add(new FileData(LocalDateTime.parse(record.split(" ")[0], formatter), Integer.parseInt(record.split(" ")[1])));
            });
        } catch (IOException | URISyntaxException e) {
            logger.log(Level.SEVERE, "Error while reading file data.", e);
        }
    }

    public List<IReport> generateReport() {
        List<IReportGenerators> reportGenerators = new ArrayList<>();
        reportGenerators.add(new TotalNumberOfVehiclesReportGenerator(fileDataList));
        reportGenerators.add(new NumberOfVehiclesEachDayReportGenerator(fileDataList));
        reportGenerators.add(new TopThreeHalfHourReportGenerator(fileDataList));
        reportGenerators.add(new ContinuousLeastHalfHourReportGenerator(fileDataList));
        List<IReport> reports = new ArrayList<>();

        reportGenerators.parallelStream().forEach(reportGenerator -> reports.add(reportGenerator.generateReport()));
        reports.stream().forEach(iReport -> iReport.getData());
        return reports;
    }

}
