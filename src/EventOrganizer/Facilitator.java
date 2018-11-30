package EventOrganizer;

import java.util.ArrayList;

public class Facilitator implements Readable, Exportable {

    // Readable Metoder
    @Override
    public void read() {
        System.out.println("Currently editing " + ID);
        System.out.println(" 1: ID \n 2: Name \n 99: Delete Facilitator " + returnOptions + exportOptions);
    } //Fra Interface Readable, tillader objektet at blive læst

    // Exportable Metoder
    @Override
    public void exportData() {
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
    public void importData() {

    } //Fra Interface Exportable, tillader objektet at blive læst fra en Arrangement fil

    private String ID = "New Facilitator";
    private String name= "John Smith";
    private ArrayList<Event> listOfEvents;
    private ArrayList<Arrangement> listOfArrangements;

    public Facilitator() {
        Filehandling.autoAddExportable(this);
        listOfEvents = new ArrayList<>();
        listOfArrangements = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Event> getListOfEvents(){
        return listOfEvents;
    }
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
    public ArrayList<Arrangement> getListOfArrangements(){
        return listOfArrangements;
    }

    public void deleteFacilitator(){
        //find filen, og slet den
    }

}
