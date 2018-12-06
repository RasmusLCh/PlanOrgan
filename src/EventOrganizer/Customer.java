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
        Filehandling.autoAddExportable(this);
        Menu.customers.add(this);
        listOfEvents = new ArrayList<>();
        listOfArrangements = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void getEvents() {
        System.out.println(name + " participates in:");
        for (Event event : listOfEvents){
            System.out.println(event.getName() + " within the arrangement " + event.getArrangement().getName());
        }
        System.out.println("\n");
    } // fortæller alle events en customer er med i.
    public void addToEventList(Event event) {
        listOfEvents.add(event);
        boolean alreadyExists = false;
        for(Arrangement arrangement : listOfArrangements){
            if(arrangement == event.getArrangement()){
                alreadyExists = true;
                break;
            }
        }
        if(!alreadyExists){
            listOfArrangements.add(event.getArrangement());
        }
    } //Tilføjer et event til liste, plus det tilhørende arrangement, hvis det ikke allerede er på.
    public void removeFromEventList(Event event){
        listOfEvents.remove(event);
        boolean keepArrangement = false;
        for(Event eventFromList : listOfEvents){
            if(eventFromList.getArrangement() == event.getArrangement()){
                keepArrangement = true;
                break;
            }
        }
        if(!keepArrangement){
            listOfArrangements.remove(event.getArrangement());
        }
    } //Fjerner et event fra listen, og det tilhørende Arrangement hvis det ikke er over andre events.

    public void deleteCustomer() {
        Menu.customers.remove(this); // fjerner fra customer listen
        Filehandling.deleteFromMaster("CUSTOMER_" + name); // sletter linje fra masterFile om customer
        Filehandling.removeExportable(this);
    }

}
