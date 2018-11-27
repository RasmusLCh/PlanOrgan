package EventOrganizer;

import java.util.ArrayList;

public class Facilitator implements Readable, Exportable {

    @Override
    public void read() {

    }
    @Override
    public void exportData() {
        System.out.println(this + " was exported as a " + getClass());
    }
    @Override
    public void importData() {
        Menu.setCurrentRead(this);
    }

    private int ID;
    private String name;
    private ArrayList<Event> listOfEvents;
    private ArrayList<Arrangement> listOfArrangements;

    public Facilitator() {
        Filehandling.autoAddExportable(this);
        listOfEvents = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }
    public void setID(int ID) {
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
    }
    public ArrayList<Arrangement> getListOfArrangements(){
        return listOfArrangements;
    }

    public void deleteFacilitator(){
        //find filen, og slet den
    }

}
