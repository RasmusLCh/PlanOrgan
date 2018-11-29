package EventOrganizer;

public class Transport extends Event {

    @Override
    public void read() {

    } //Fra Interface Readable, tillader objektet at blive læst
    @Override
    public void exportData() {
        Filehandling.writeToLine("ARRANGEMENT_" + getArrangement().getName(), "Transport," + getFacilitator().getName() + "," + getDescription() + "," + getStartTime() + "," + getEndTime() + "," + getComment() + "," + getLocation() + "," + getCustomer().getName() + "," + destination + "," + vehicle, getID());

    } //Fra Interface Exportable, tillader objektet at blive eksporteret til en Arrangement fil
    @Override
    public void importData() {
        Menu.setCurrentRead(this);
    } //Fra Interface Exportable, tillader objektet at blive læst fra en Arrangement fil

    private String destination = "";
    private String vehicle = "";

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
