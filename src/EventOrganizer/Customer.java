package EventOrganizer;

import java.util.ArrayList;

public class Customer implements Exportable, Readable {

    private String name;
    private ArrayList<Arrangement> listOfArrangements;
    private ArrayList<Event> listOfEvents;

    @Override
    public void readEditInfo() {
        System.out.println("Currently editing " + name);
        System.out.println(" 1: Check all associated events \n 99: Delete Customer " + returnOptions + exportOptions);
    }
    @Override
    public void exportData() {
        Filehandling.writeToMaster("CUSTOMER_" + name);
    }
    @Override
    public void importData(String data) {

    }

    public Customer (String name) {
        this.name = name;
        Menu.customers.add(this);
        listOfEvents = new ArrayList<>();
        listOfArrangements = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void getEvents() {
        System.out.println(name + " participates in:");
        for (int i = 0; i < listOfEvents.size(); i++){
            System.out.println(listOfEvents.get(i).getName() + " within the arrangement " + listOfEvents.get(i).getArrangement().getName());
        }
        System.out.println("\n");
    } // fortæller alle events en customer er med i.
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

    public void deleteCustomer() {

    } //UPDATE THIS

}
