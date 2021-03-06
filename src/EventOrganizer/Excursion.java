package EventOrganizer;

public class Excursion extends Event {

    // Readable Metoder
    @Override
    public void readEditInfo() {
        System.out.println("Currently editing " + getName()  + " within " + getArrangement().getName());
        System.out.println(" 1: ID \n 2: Name \n 3: Description \n 4: Start Time \n 5: End Time \n 6: Facilitator \n 7: Comment \n 8: Location \n 9: Customer \n 11: Destination \n 99: Delete Event " + returnOptions + exportOptions);
    } //Fra Interface Readable, tillader objektet at blive læst af brugeren

    // Exportable Metoder
    @Override
    public void exportData() {
        Filehandling.writeToLine("ARRANGEMENT_" + getArrangement().getName(), "Excursion," + getName() + "," + getFacilitator() + "," + getDescription() + "," + getStartTime() + "," + getEndTime() + "," + getComment() + "," + getLocation() + "," + getCustomer() + "," + destination  + ",", getID());
    } //Fra Interface Exportable, tillader objektet at blive eksporteret til en Arrangement fil
    @Override
    public void importData(String data) {
        String[] eventData = data.split(",");
        setName(eventData[1]);
        for (Facilitator facilitator : Menu.facilitators){
            if(facilitator.getID().equals(eventData[2])){
                setFacilitator(facilitator.getID());
                break;
            }
        }
        setDescription(eventData[3]);
        setStartTime(eventData[4]);
        setEndTime(eventData[5]);
        setComment(eventData[6]);
        setLocation(eventData[7]);
        setCustomer(eventData[8]);
        setDestination(eventData[9]);
    } //Fra Interface Exportable, tillader objektet at blive oprettet fra en Arrangement fil

    private String destination = "Destination";

    public Excursion(Arrangement arrangement) {
        setArrangement(arrangement); // Sætter eventets arrangement
        arrangement.getEventList().add(this); // Tilføjer til arrangements eventlist
        Filehandling.autoAddExportable(this); // Tilføjer til allExports i filehandling
        Menu.events.add(this); // tilføjer til liste over alle events
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

}
