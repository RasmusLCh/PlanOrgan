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

    private DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // importeret klasse, dag/tids format
    private int ID = 1;
    private String name = "New Event";
    private String description = "Insert description here";
    private Arrangement arrangement = null;
    private LocalDateTime startTime = LocalDateTime.now(); //LocalDateTime er en importeret klasse der holder styr på tid.
    private LocalDateTime endTime = LocalDateTime.now();
    private Facilitator facilitator = null;
    private String comment = "Comment";
    private String location = "Location";
    private Customer customer = null;

    public int getID(){
        return this.ID;
    }
    public void setID(int ID){
        if (ID < 1){ // ID skal være 1 eller højere
            System.out.println("ID must be 1 or Higher!");
            return;
        } else {
            for(int i = 0; i < arrangement.getEventList().size(); i++){ // tjekker alle events under arrangement igennem, for en der har samme ID
                if(arrangement.getEventList().get(i).getID() == ID){
                    System.out.println("Event with that ID already exists under Arrangement " + arrangement.getName()); // hvis ID allerede eksisterer, skiftes ID ikke
                    return;
                }
            }
            Filehandling.writeToLine( "ARRANGEMENT_" + getArrangement().getName(), "NO EVENT WITH THIS ID", this.ID); //fjerner gammel info hvis det lykkedes
            this.ID = ID;
        }
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
        String returnString = startTime.toString(); // konverterer endtime til string
        returnString = returnString.replace('T', ' '); // erstatter T med mellemrum
        if(returnString.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            returnString += ":00";
        }
        returnString = returnString.substring(0, 19); // skærer string ned til de rigtige cifrer
        return returnString;
    }
    public void setStartTime(String startTime) {
        startTime = startTime.replace('T', ' '); // erstatter T med mellemrum
        if(startTime.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            startTime += ":00";
        }
        if(startTime.charAt(4) == '-' && startTime.charAt(7) == '-' && startTime.charAt(10) == ' ' && startTime.charAt(13) == ':' && startTime.charAt(16) == ':') { // tjekker format
            LocalDateTime arrangementStartTime = (LocalDateTime.parse(arrangement.getStartTime().substring(0, 19), dTFormat));
            LocalDateTime arrangementEndTime = (LocalDateTime.parse(arrangement.getEndTime().substring(0, 19), dTFormat));
            LocalDateTime eventStartTime = (LocalDateTime.parse(startTime.substring(0, 19), dTFormat));
            if(Duration.between(arrangementStartTime, eventStartTime).toMinutes() >= 0 && Duration.between(arrangementEndTime, eventStartTime).toMinutes() <= 0 ){ //tjekker om tiden er indenfor arrangementet
                if(Duration.between(eventStartTime, endTime).toMinutes() >= 0) { //tjekker om tiden er før endTime
                    this.startTime = eventStartTime;
                } else {
                    System.out.println("Start time must be before the end time.");
                }
            } else {
                if(Menu.importDone) {
                    System.out.println("Start time must be within the borders of the Arrangement.");
                }
            }
        } else {
        System.out.println(startTime + " is not a valid time.");
        }
    }
    public String getEndTime() {
        String returnString = endTime.toString(); // konverterer endtime til string
        returnString = returnString.replace('T', ' '); // erstatter T med mellemrum
        if(returnString.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            returnString += ":00";
        }
        returnString = returnString.substring(0, 19); // skærer string ned til de rigtige cifrer
        return returnString;
    }
    public void setEndTime(String endTime) {
        endTime = endTime.replace('T', ' '); // erstatter T med mellemrum
        if(endTime.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            endTime += ":00";
        }
        if(endTime.charAt(4) == '-' && endTime.charAt(7) == '-' && endTime.charAt(10) == ' ' && endTime.charAt(13) == ':' && endTime.charAt(16) == ':') { // tjekker format
            LocalDateTime arrangementStartTime = (LocalDateTime.parse(arrangement.getStartTime().substring(0, 19), dTFormat));
            LocalDateTime arrangementEndTime = (LocalDateTime.parse(arrangement.getEndTime().substring(0, 19), dTFormat));
            LocalDateTime eventEndTime = (LocalDateTime.parse(endTime.substring(0, 19), dTFormat));
            if(Duration.between(arrangementStartTime, eventEndTime).toMinutes() >= 0 && Duration.between(arrangementEndTime, eventEndTime).toMinutes() <= 0 ){ //tjekker om tiden er indenfor arrangementet
                if(Duration.between(eventEndTime, startTime).toMinutes() <= 0) { //tjekker om tiden er efter startTime
                    this.endTime = eventEndTime;
                } else {
                    System.out.println("End time must be after the start time.");
                }
            } else {
                if(Menu.importDone) {
                    System.out.println("End time must be within the borders of the Arrangement.");
                }
            }
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
            this.facilitator.removeFromEventList(this); //fjerner gammel facilitator fra eventet
        }
        for (int i = 0; i < Menu.facilitators.size(); i++){ //søger customer listen igennem for at finde en med det rigtige navn
            if(Menu.facilitators.get(i).getID().equals(facilitator)){
                this.facilitator = Menu.facilitators.get(i); // tilføjer den her.
                this.facilitator.addToEventList(this); // tilføjer den i dens egen instans.
                return;
            }
        }
        if(Menu.importDone) {
            System.out.println("There appears to be no facilitator with that ID. \n");
        }
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
            this.customer.removeFromEventList(this); //fjerner gammel customer fra eventet
        }
        for (int i = 0; i < Menu.customers.size(); i++){ //søger customer listen igennem for at finde en med det rigtige navn
            if(Menu.customers.get(i).getName().equals(customer)){
                this.customer = Menu.customers.get(i); // tilføjer den her.
                this.customer.addToEventList(this); // tilføjer den i dens egen instans.
                return;
            }
        }
        if (Menu.importDone){
            System.out.println("There appears to be no customer with that name. \n");
        }
    }
    public float calculatePrice(){
        float price = 0;
        long duration = Duration.between(startTime, endTime).toMinutes(); // læser tiden mellem start og slut
        price = 100; // 100 kr oprettetlse
        if (startTime.getDayOfWeek() == DayOfWeek.SATURDAY || startTime.getDayOfWeek() == DayOfWeek.SUNDAY){ // hvis der er lørdag eller søndag er prisen 350 per ½time
            price += 350 * (duration / 30);
            if(!(duration % 30 == 0)){ // Modulus, hvis en ny ½time er startet, tæller den en ekstra gang
                price += 350;
            }
        } else { // ellers er prisen 250 per ½time
            price += 250 * (duration / 30);
            if(!(duration % 30 == 0)){ // Modulus, hvis en ny ½time er startet, tæller den en ekstra gang
                price += 250;
            }
        }
    return price;
    }

    public void deleteEvent(){
        Filehandling.writeToLine( "ARRANGEMENT_" + getArrangement().getName(), "NO EVENT WITH THIS ID", ID); // Erstatter linjen i arrangement filen
        Menu.events.remove(this); //fjerner fra lister, her og ned
        arrangement.getEventList().remove(this);
        facilitator.removeFromEventList(this);
        customer.removeFromEventList(this);
        Filehandling.removeExportable(this);
    } //finder den relevante arrangement fil, og sletter information, kun om dette event, ved at erstatte linjen, og fjerner fra lister


}
