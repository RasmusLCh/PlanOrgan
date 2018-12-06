package EventReader;

import EventOrganizer.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Filehandling {

    private static final File masterFile = new File ("files.txt");
    private static File readingFile;
    private static String readingString;

    public static boolean findFacilitator(String ID){
        try {
            Path path = Paths.get(masterFile.getName());
            List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            int end = 0;
                for (int i = 0; i < content.size(); i++){
                if (content.get(i).charAt(0) == 'c'){
                    end = i;
                    break;
                }
            }
            for (int i = 1; i < end; i++){
                if (content.get(i).equals("FACILITATOR_" + ID)){
                    return true;
                }
            }
            return false;


        } catch (FileNotFoundException fnfe) {}  catch (IOException ioe) {}
        return false;
    } // metode til at finde facilitator fil. Returnerer en bool, true hvis det lykkedes, ellers false

    public static void importData(String filename){
        try {
            System.out.println("Facilitator found!");
            readingFile = new File(filename + ".csv"); //Baseret på linjen finder den en fil til at læse.
            Scanner readingScanner = new Scanner(readingFile);
            Facilitator newFacilitator = new Facilitator();
            newFacilitator.setID(filename.substring(12));
            newFacilitator.setName(readingScanner.nextLine()); //Information om Events og Arrangementer bliver tilføjet til en faciliatotor gennem Event's ImportData
            readingString = readingScanner.nextLine();
            String[] arrangementData = readingString.split(",");
            for (int i = 0; i < arrangementData.length; i++){
                int linecount = 0; //Int til at tælle den nuværende linje, bliver brugt til at afgøre ID for Events
                readingFile = new File("ARRANGEMENT_" + arrangementData[i] + ".csv"); //Baseret på linjen finder den en fil til at læse.
                Arrangement newArrangement = new Arrangement();
                newArrangement.setName(arrangementData[i]);
                readingScanner = new Scanner(readingFile);
                readingString = readingScanner.nextLine();
                newArrangement.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                while(readingScanner.hasNextLine()){ // Læser event information mens vi er i Arrangement filen
                    linecount++; // Øger linecount med 1
                    readingString = readingScanner.nextLine();
                    Event newEvent;
                    if(readingString.charAt(0) == 'E'){
                        newEvent = new Excursion(newArrangement);
                        String[] eventData = readingString.split(",");
                        new Customer(eventData[8]);
                        newEvent.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                        newEvent.setID(linecount);
                    } else if(readingString.charAt(0) == 'T'){
                        newEvent = new Transport(newArrangement);
                        String[] eventData = readingString.split(",");
                        new Customer(eventData[8]);
                        newEvent.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                        newEvent.setID(linecount);
                    } else if(readingString.charAt(0) == 'M'){
                        newEvent = new Meeting(newArrangement);
                        String[] eventData = readingString.split(",");
                        new Customer(eventData[8]);
                        newEvent.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                        newEvent.setID(linecount);
                    }
                }
            }
            Menu.readFacilitator(newFacilitator);
        } catch (FileNotFoundException fnfe) {}
    } // importerer data ud fra hvad der står i facilitator filen
}
