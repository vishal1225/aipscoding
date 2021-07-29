import org.junit.Assert;
import org.junit.Test;
import report.generators.ContinuousLeastHalfHourReportGenerator;
import report.generators.NumberOfVehiclesEachDayReportGenerator;
import report.generators.TopThreeHalfHourReportGenerator;
import report.generators.TotalNumberOfVehiclesReportGenerator;
import report.processors.FileData;
import report.processors.FileProcessor;
import reports.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileProcessorTest {
    String filePath = "sample.txt";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Test
    public void testReportFileProcessing() {
        FileProcessor fileProcessor = new FileProcessor();
        fileProcessor.readFile(filePath);
        List<IReport> reportList = fileProcessor.generateReport();
        Assert.assertEquals("Confirm the number of report", 4, reportList.size());
    }

    @Test
    public void testContinuousLeastHalfHourReportGenerator() {
        List<FileData> fileDataList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(filePath).toURI()))) {
            stream.forEach(record -> {
                fileDataList.add(new FileData(LocalDateTime.parse(record.split(" ")[0], formatter), Integer.parseInt(record.split(" ")[1])));
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        ContinuousLeastHalfHourReportGenerator continuousLeastHalfHourReportGenerator = new ContinuousLeastHalfHourReportGenerator(fileDataList);
        IReport report = continuousLeastHalfHourReportGenerator.generateReport();
        Assert.assertTrue(report instanceof ContinuousLeastHalfHourReport);
    }

    @Test
    public void testNumberOfCarsPerDayReportGenerator() {
        List<FileData> fileDataList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(filePath).toURI()))) {
            stream.forEach(record -> {
                fileDataList.add(new FileData(LocalDateTime.parse(record.split(" ")[0], formatter), Integer.parseInt(record.split(" ")[1])));
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        NumberOfVehiclesEachDayReportGenerator numberOfVehiclesEachDayReportGenerator = new NumberOfVehiclesEachDayReportGenerator(fileDataList);
        IReport report = numberOfVehiclesEachDayReportGenerator.generateReport();
        Assert.assertTrue(report instanceof NumberOfCarsPerDayReport);
    }

    @Test
    public void testTopThreeHalfHourReportGenerator() {
        List<FileData> fileDataList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(filePath).toURI()))) {
            stream.forEach(record -> {
                fileDataList.add(new FileData(LocalDateTime.parse(record.split(" ")[0], formatter), Integer.parseInt(record.split(" ")[1])));
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        TopThreeHalfHourReportGenerator topThreeHalfHourReportGenerator = new TopThreeHalfHourReportGenerator(fileDataList);
        IReport report = topThreeHalfHourReportGenerator.generateReport();
        Assert.assertTrue(report instanceof TopThreeHalfHourReport);
    }
    @Test
    public void testTotalNumberOfVehiclesReportGenerator() {
        List<FileData> fileDataList = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(ClassLoader.getSystemResource(filePath).toURI()))) {
            stream.forEach(record -> {
                fileDataList.add(new FileData(LocalDateTime.parse(record.split(" ")[0], formatter), Integer.parseInt(record.split(" ")[1])));
            });
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        TotalNumberOfVehiclesReportGenerator totalNumberOfVehiclesReportGenerator = new TotalNumberOfVehiclesReportGenerator(fileDataList);
        IReport report = totalNumberOfVehiclesReportGenerator.generateReport();
        Assert.assertTrue(report instanceof TotalNumberOfVehiclesReport);
    }
}
