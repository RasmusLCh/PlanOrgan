package EventOrganizer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Arrangement implements Readable, Exportable {

    // Readable Metoder
    @Override
    public void readEditInfo() {
        System.out.println("Currently editing " + name);
        System.out.println("Runs from " + getStartTime() + " to " + getEndTime());
        System.out.println("Currently slated price: " + getPrice() + " without VAT, or " + (getPrice() * 1.25 ) + " with VAT");
        System.out.println(" 1: Name \n 2: Start Time \n 3: End Time \n 4: View Events \n 5: View Customers \n 99: Delete Arrangement " + returnOptions + exportOptions);
    } //Fra Interface Readable, tillader objektet at blive læst af brugeren

    // Exportable Metoder
    @Override
    public void exportData() { //UPDATE THIS / MASTERFILE
        Filehandling.writeToMaster("ARRANGEMENT_" + name);
        Filehandling.writeToLine("ARRANGEMENT_" + name,getStartTime() + "," + getEndTime() + "," + getPrice(), 0);
    } // Tillader objektet at blive eksporteret til en Arrangement fil
    @Override
    public void importData(String data) {
        String[] times = data.split(",");
        times[0] = times[0].replace('T', ' ');
        times[1] = times[1].replace('T', ' ');
        setStartTime(times[0]);
        setEndTime(times[1]);

    } // Tillader objektet at blive oprettet fra en Arrangement fil

    private DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String name = "New Arrangement";
    private ArrayList<Event> listOfEvents = new ArrayList<>();
    private LocalDateTime startTime = LocalDateTime.now(); //LocalDateTime er en importeret klasse der holder styr på tid.
    private LocalDateTime endTime = LocalDateTime.now();

    public Arrangement() {
        Filehandling.autoAddExportable(this); // tilføjer til allExports
        Menu.arrangements.add(this); // tilføjer til liste over arrangementer
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        Filehandling.deleteFile("ARRANGEMENT_" + name); // sletter gammel fil når navn ændres
        this.name = name;
    }
    public ArrayList<Event> getEventList (){
        return listOfEvents;
    }
    public String getStartTime() {
        String returnString = startTime.toString(); // konverterer startTime til string
        returnString = returnString.replace('T', ' '); // erstatter T med mellemrum
        if(returnString.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            returnString += ":00";
        }
        returnString = returnString.substring(0, 19); // skærerer returnString til, så den passer formattet
        return returnString;
    }
    public void setStartTime(String startTime) {
        startTime = startTime.replace('T', ' '); // Erstatter T med mellemrum
        if(startTime.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            startTime += ":00";
        }
        if(startTime.charAt(4) == '-' && startTime.charAt(7) == '-' && startTime.charAt(10) == ' ' && startTime.charAt(13) == ':' && startTime.charAt(16) == ':') { // tjekker at imput følger formattet
            LocalDateTime arrangementStartTime = (LocalDateTime.parse(startTime.substring(0, 19), dTFormat));
            boolean invalidTime = false;
            for(int i = 0; i < listOfEvents.size(); i++){ // går igennem alle events i arrangementet
                LocalDateTime eventStartTime = (LocalDateTime.parse(listOfEvents.get(i).getStartTime().substring(0, 19), dTFormat));
                if(!(Duration.between(arrangementStartTime, eventStartTime).toMinutes() >= 0)){ // hvis den finder en der er ude for tiden, kan den ikke ændre tiden.
                    invalidTime = true;
                }
            }
            if(invalidTime){ // hvis tiden ikke er godkendt får brugeren en fejl
                System.out.println("Start time must be before start of the first event!");
            } else {
                this.startTime = arrangementStartTime;
            }
        } else {
            System.out.println(startTime + " is not a valid time."); // hvis formattet er forkert får brugeren en fejl
        }
    }
    public String getEndTime() {
        String returnString = endTime.toString(); // konverterer endTime til string
        returnString = returnString.replace('T', ' '); // erstatter T med mellemrum
        if(returnString.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            returnString += ":00";
        }
        returnString = returnString.substring(0, 19); // skærerer returnString til, så den passer formattet
        return returnString;
    }
    public void setEndTime(String endTime) {
        endTime = endTime.replace('T', ' '); // Erstatter T med mellemrum
        if(endTime.length() == 16){    //Hvis sekunder ikke eksisterer i variablen, tilføjer vi den som 00
            endTime += ":00";
        }
        if(endTime.charAt(4) == '-' && endTime.charAt(7) == '-' && endTime.charAt(10) == ' ' && endTime.charAt(13) == ':' && endTime.charAt(16) == ':') { // tjekker at imput følger formattet
            LocalDateTime arrangementEndTime = (LocalDateTime.parse(endTime.substring(0, 19), dTFormat));
            boolean invalidTime = false;
            for(int i = 0; i < listOfEvents.size(); i++){ // går igennem alle events i arrangementet
                LocalDateTime eventEndTime = (LocalDateTime.parse(listOfEvents.get(i).getEndTime().substring(0, 19), dTFormat));
                if(!(Duration.between(arrangementEndTime, eventEndTime).toMinutes() >= 0)){ // hvis den finder en der er ude for tiden, kan den ikke ændre tiden.
                    invalidTime = true;
                }
            }
            if(invalidTime){ // hvis tiden ikke er godkendt får brugeren en fejl
                System.out.println("End time must be after the end of the last event!");
            } else {
                this.endTime = arrangementEndTime;
            }
        } else {
            System.out.println(endTime + " is not a valid time."); // hvis formattet er forkert får brugeren en fejl
        }
    }
    public void getEvents(){
        System.out.println("Under " + name + " exists events:");
        for (int i = 0; i < listOfEvents.size(); i++){
            System.out.println(listOfEvents.get(i).getName()); // går gennem events i arrangementet, og viser navnet på det til brugeren
        }
        System.out.println("\n");
    } // fortæller alle events under et arrangement.
    public void getCustomers(){
        System.out.println("Under " + name + " is the customers:");
        for (int i = 0; i < listOfEvents.size(); i++){
            System.out.println(listOfEvents.get(i).getCustomer() + " in Event " + listOfEvents.get(i).getName()); // går gennem alle customers, fortæller hvem de er, og hvilket event de er under
        }
        System.out.println("\n");
    }
    public float getPrice() {
        float price = 0;
        for(int i = 0; i < listOfEvents.size(); i++){
            price += listOfEvents.get(i).calculatePrice(); // går gennem alle events under arrangementet og læggger prisen sammen
        }
        return price;
    }

    public void deleteArrangement(){
        Menu.arrangements.remove(this); // fjerner fra listen over arrangementer
        for (int i = 0; i < listOfEvents.size(); i++){
            listOfEvents.get(i).deleteEvent(); // et for loop der kalder deleteEvent i alle events under arrangement
        }
        Filehandling.deleteFile("ARRANGEMENT_" + name); // sletter filen gennem filehandling
        Filehandling.removeExportable(this); // fjerner fra all export listen
    }
}