package EventOrganizer;

public class Transport extends Event {

    // Readable Metoder
    @Override
    public void read() {
        System.out.println("Currently editing " + getID() + " within " + getArrangement().getName());
        System.out.println(" 1: ID \n 2: Name \n 3: Description \n 4: Start Time \n 5: End Time \n 6: Facilitator \n 7: Comment \n 8: Location \n 9: Customer \n 11: Destination \n 12: Vehicle \n 99: Delete Event " + returnOptions + exportOptions);
    } //Fra Interface Readable, tillader objektet at blive læst

    // Exportable Metoder
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
