package EventOrganizer;

import java.util.ArrayList;

public class Facilitator implements Readable, Exportable {

    // Readable Metoder
    @Override
    public void readEditInfo() {
        System.out.println("Currently editing " + ID);
        System.out.println(" 1: ID \n 2: Name \n 3: Check all associated events \n 99: Delete Facilitator " + returnOptions + exportOptions);
    } //Fra Interface Readable, tillader objektet at blive læst af brugeren

    // Exportable Metoder
    @Override
    public void exportData() { //UPDATE THIS / MASTERFILE
        Filehandling.writeToMaster("FACILITATOR_" + ID);
        String arrangementList = "";
        for (int i = 0; i < listOfArrangements.size(); i++){
            arrangementList += listOfArrangements.get(i).getName() + ",";
        }
        String eventList = "";
        for (int i = 0; i < listOfEvents.size(); i++){
            eventList += listOfEvents.get(i).getName() + ",";
        }
        Filehandling.writeToLine("FACILITATOR_" + ID, name, 0);
        Filehandling.writeToLine("FACILITATOR_" + ID, arrangementList, 1);
        Filehandling.writeToLine("FACILITATOR_" + ID, eventList, 2);
    } //Fra Interface Exportable, tillader objektet at blive eksporteret til en Arrangement fil
    @Override
    public void importData(String data) {

    } //Fra Interface Exportable, tillader objektet at blive oprettet fra en Facilitator fil

    private String ID = "New Facilitator";
    private String name= "New Facilitator";
    public ArrayList<Event> listOfEvents;
    private ArrayList<Arrangement> listOfArrangements;

    public Facilitator() {
        Filehandling.autoAddExportable(this); // tilføjer til allExports
        Menu.facilitators.add(this); // tilføjer til liste over facilitators
        listOfEvents = new ArrayList<>();
        listOfArrangements = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        Filehandling.deleteFile("FACILITATOR_" + this.ID); // Sletter den gamle fil når ID bliver ændret, fordi ID afgør filnavn
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void getEvents(){
        System.out.println(ID + " participates in:");
        for (int i = 0; i < listOfEvents.size(); i++){
            System.out.println(listOfEvents.get(i).getName() + " within the arrangement " + listOfEvents.get(i).getArrangement().getName());
        }
    } // fortæller alle events en facilitator er med i.
    public void addToEventList(Event event){
        listOfEvents.add(event);
        boolean alreadyExists = false;
        for(int i = 0; i < listOfArrangements.size(); i++){
            if(listOfArrangements.get(i) == event.getArrangement()){
                alreadyExists = true;
                break;
            }
        }
        if(!alreadyExists){
            listOfArrangements.add(event.getArrangement());
        }
    } //Tilføjer et event til facilitators liste, plus det tilhørende arrangement, hvis det ikke allerede er på.
    public void removeFromEventList(Event event){
        listOfEvents.remove(event);
        boolean keepArrangement = false;
        for(int i = 0; i < listOfEvents.size(); i++){
            if(listOfEvents.get(i).getArrangement() == event.getArrangement()){
                keepArrangement = true;
                break;
            }
        }
        if(!keepArrangement){
            listOfArrangements.remove(event.getArrangement());
        }
    } //Fjerner et event fra listen, og det tilhørende Arrangement hvis det ikke er over andre events.

    public void deleteFacilitator(){
        Menu.facilitators.remove(this); //fjerner fra facilitator liste
        Filehandling.deleteFile("FACILITATOR_" + ID); //kalder deleteFile i filehandling, for at slette filen selv
        Filehandling.removeExportable(this); // fjerner fra allExports listen
    }

}
