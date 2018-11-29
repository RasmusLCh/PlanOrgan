package EventOrganizer;

import java.util.ArrayList;

public class Customer implements Exportable {

    private String name;
    private ArrayList<Arrangement> listOfArrangements;
    private ArrayList<Event> listOfEvents;

    @Override
    public void exportData() {

    }
    @Override
    public void importData() {

    }

    public Customer (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addToEventList(Event event) {
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

    public void deleteCustomer() {

    }

}
