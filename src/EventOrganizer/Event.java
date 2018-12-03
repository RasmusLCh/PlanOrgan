package EventOrganizer;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event implements Readable, Exportable {


    @Override
    public void readEditInfo() { } //Events af en uspecificeret type kan ikke læses
    @Override
    public void exportData() { } //Events af en uspecificeret type kan ikke exporteres
    @Override
    public void importData(String data) { } //Events af en uspecificeret type kan ikke importeres

    private DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private int ID = 0;
    private String name = "New Event";
    private String description = "Insert description here";
    private Arrangement arrangement = null;
    private LocalDateTime startTime = LocalDateTime.now(); //LocalDateTime er en importeret klasse der holder styr på tid.
    private LocalDateTime endTime = LocalDateTime.now();
    private Facilitator facilitator = null;
    private String comment = "";
    private String location = "";
    private Customer customer;

    public int getID(){
        return this.ID;
    }
    public void setID(int ID){
        deleteEvent();
        this.ID = ID;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    public Arrangement getArrangement() {
        return arrangement;
    }
    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
    }
    public String getStartTime() {
        String returnString = startTime.toString();
        returnString = returnString.replace('T', ' ');
        if(returnString.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            returnString += ":00";
        }
        returnString = returnString.substring(0, 19);
        return returnString;
    }
    public void setStartTime(String startTime) {
        startTime = startTime.replace('T', ' ');
        if(startTime.charAt(4) == '-' && startTime.charAt(7) == '-' && startTime.charAt(10) == ' ' && startTime.charAt(13) == ':' && startTime.charAt(16) == ':') {
            this.startTime = (LocalDateTime.parse(startTime.substring(0, 19), dTFormat));
        } else {
        System.out.println(startTime + " is not a valid time.");
        }
    }
    public String getEndTime() {
        String returnString = endTime.toString();
        returnString = returnString.replace('T', ' ');
        if(returnString.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            returnString += ":00";
        }
        returnString = returnString.substring(0, 19);
        return returnString;
    }
    public void setEndTime(String endTime) {
        endTime = endTime.replace('T', ' ');
        if(endTime.charAt(4) == '-' && endTime.charAt(7) == '-' && endTime.charAt(10) == ' ' && endTime.charAt(13) == ':' && endTime.charAt(16) == ':') {
            this.endTime = (LocalDateTime.parse(endTime.substring(0, 19), dTFormat));
        } else {
            System.out.println(endTime + " is not a valid time.");
        }
    }
    public String getFacilitator() {
        if(facilitator != null){
            return facilitator.getID();
        } else {
            System.out.println("Event " + getName() + " within arrangement " + getArrangement().getName() + " does not have a set facilitator.");
            return "";
        }
    }
    public void setFacilitator(String facilitator) {
        if(this.facilitator != null){
            this.facilitator.removeFromEventList(this);
        }
        for (int i = 0; i < Menu.facilitators.size(); i++){
            if(Menu.facilitators.get(i).getID().equals(facilitator)){
                this.facilitator = Menu.facilitators.get(i);
                this.facilitator.addToEventList(this);
                return;
            }
        }
        System.out.println("There appears to be no facilitator with that ID. \n");
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getCustomer() {
        if(customer != null){
            return customer.getName();
        } else {
            System.out.println("Event " + getName() + " within arrangement " + getArrangement().getName() + " does not have a set customer.");
            return "";
        }
    }
    public void setCustomer(String customer) {
        if(this.customer != null){
            this.customer.removeFromEventList(this);
        }
        for (int i = 0; i < Menu.customers.size(); i++){
            if(Menu.customers.get(i).getName().equals(customer)){
                this.customer = Menu.customers.get(i);
                this.customer.addToEventList(this);
                return;
            }
        }
        System.out.println("There appears to be no customer with that name. \n");
    }
    public float calculatePrice(){
        float price = 0;
        long duration = Duration.between(startTime, endTime).toMinutes();
        price = 100;
        if (startTime.getDayOfWeek() == DayOfWeek.SATURDAY || startTime.getDayOfWeek() == DayOfWeek.SUNDAY){
            price += 350 * (duration / 30);
            if(!(duration % 30 == 0)){
                price += 350;
            }
        } else {
            price += 250 * (duration / 30);
            if(!(duration % 30 == 0)){
                price += 250;
            }
        }
    return price;
    }

    public void deleteEvent(){
        Filehandling.writeToLine( "ARRANGEMENT_" + getArrangement().getName(), "NO EVENT WITH THIS ID", ID);
        Menu.events.remove(this);
    } //finder den relevante arrangement fil, og sletter information, kun om dette event, ved at erstatte linjen


}
