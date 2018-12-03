package EventOrganizer;

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
        System.out.println(" 1: Name \n 99: Delete Arrangement " + returnOptions + exportOptions);
    } //Fra Interface Readable, tillader objektet at blive læst af brugeren

    // Exportable Metoder
    @Override
    public void exportData() { //UPDATE THIS / MASTERFILE
        Filehandling.writeToMaster("ARRANGEMENT_" + name);
        Filehandling.writeToLine("ARRANGEMENT_" + name,startTime.toString() + "," + endTime.toString() + "," + getPrice(), 0);
    } // Tillader objektet at blive eksporteret til en Arrangement fil
    @Override
    public void importData(String data) {
        String[] times = data.split(",");
        times[0] = times[0].replace('T', ' ');
        times[1] = times[1].replace('T', ' ');
        setStartTime(times[0]);
        setEndTime(times[1]);

    } // Tillader objektet at blive oprettet fra en Arrangement fil

    public ArrayList<Event> listOfEvents = new ArrayList<>();
    private DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private String name = "New Arrangement";
    private LocalDateTime startTime = LocalDateTime.now(); //LocalDateTime er en importeret klasse der holder styr på tid.
    private LocalDateTime endTime = LocalDateTime.now();

    public Arrangement() {
        Filehandling.autoAddExportable(this);
        Menu.arrangements.add(this);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        float price = 0;
        for(int i = 0; i < listOfEvents.size(); i++){
            price += listOfEvents.get(i).calculatePrice();
        }
        return price;
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
        this.startTime = (LocalDateTime.parse(startTime.substring(0, 19), dTFormat));
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
        this.endTime = (LocalDateTime.parse(endTime.substring(0, 19), dTFormat));
    }

    public void deleteArrangement(){
    } //UPDATE THIS       //finder arrangement filen, og sletter den

}