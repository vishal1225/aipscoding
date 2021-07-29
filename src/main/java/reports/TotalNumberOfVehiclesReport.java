package reports;

public class TotalNumberOfVehiclesReport implements IReport {
    public TotalNumberOfVehiclesReport(int totalNumberOfVehicles) {
        this.totalNumberOfVehicles = totalNumberOfVehicles;
    }

    private int totalNumberOfVehicles;

    @Override
    public void getData() {
        System.out.println("Total Number of vehicles is ".concat(String.valueOf(this.totalNumberOfVehicles)));
    }
}
