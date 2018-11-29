package EventOrganizer;

public class Transport extends Event {

    @Override
    public void read() {

    }
    @Override
    public void exportData() {
//        Filehandling.writeToFile(getArrangement().getName(), "transport");

    }
    @Override
    public void importData() {
        Menu.setCurrentRead(this);
    }

    private String destination;
    private String vehicle;

    public Transport() {
        Filehandling.autoAddExportable(this);
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }


}
