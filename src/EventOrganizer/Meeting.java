package EventOrganizer;

public class Meeting extends Event {

    // Readable Metoder
    @Override
    public void read() {
        System.out.println("Currently editing " + getID() + " within " + getArrangement().getName());
        System.out.println(" 1: ID \n 2: Name \n 3: Description \n 4: Start Time \n 5: End Time \n 6: Facilitator \n 7: Comment \n 8: Location \n 9: Customer \n 11: Equipment \n 99: Delete Event " + returnOptions + exportOptions);
    } //Fra Interface Readable, tillader objektet at blive læst

    // Exportable Metoder
    @Override
    public void exportData() {
        Filehandling.writeToLine("ARRANGEMENT_" + getArrangement().getName(), "Transport," + getFacilitator().getName() + "," + getDescription() + "," + getStartTime() + "," + getEndTime() + "," + getComment() + "," + getLocation() + "," + getCustomer().getName() + "," + equipment, getID());    } // Tillader objektet at blive eksporteret til en Arrangement fil
    @Override
    public void importData() {

    } // Tillader objektet at blive læst fra en Arrangement fil

    private String[] equipment;

    public Meeting() {
        Filehandling.autoAddExportable(this);
    }

    public String[] getEquipment() {
        return equipment;
    }
    public void setEquipment(String[] tools) {
        this.equipment = tools;
    }

}
