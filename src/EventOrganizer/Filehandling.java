package EventOrganizer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filehandling {

    private static final File masterFile = new File ("files.txt");
    private static File readingFile;

    /* Export sektion - Alt her er relevant for export af data */
    private static ArrayList<Exportable> selectedExports = new ArrayList<>(); // En liste over udvalgte objekter
    private static ArrayList<Exportable> allExports = new ArrayList<>(); // En liste over alle objekter der er blevet oprettet/redigeret i denne session.

    public static void addExportable(Exportable exportable){
        selectedExports.add(exportable);
    } //metode der tilføjer et objekt til den selektive liste.
    public static void executeExport(){
        for (int i = 0; i < selectedExports.size(); i++){
            selectedExports.get(i).exportData();
        }
    } //metode der eksporterer alt fra den selektive liste.
    public static void autoAddExportable(Exportable exportable){
        allExports.add(exportable);
    } //Metode der bliver kaldt som følge af en konstruktor, som tilføjer objektet til allExports listen.
    public static void exportAll(){
        for (int i = 0; i < allExports.size(); i++){
            allExports.get(i).exportData();
        }
    } //Exporterer alle objekter i allExports listen.
    public static void removeExportable(Exportable exportable){
        selectedExports.remove(exportable);
        allExports.remove(exportable);
    } //metode der fjerner et objekt fra den begge lister.

    public static void writeToLine(String fileName, String text, int line) {
        readingFile = new File(fileName + ".csv");
        try {
            readingFile.createNewFile(); // laver en ny fil hvis en af samme navn ikke allerede eksisterer. Retunerer en boolean som vi ikke bruger til noget.
            Path path = Paths.get(fileName + ".csv");
            List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            if(content.size() < line + 1){
                List<String> newLines = new ArrayList<>();
//                newLines.add("NO EVENT WITH THIS ID");
                for (int i = 0; i <= line; i++){
//                    newLines.add("NO EVENT WITH THIS ID");
                    content.add("NO EVENT WITH THIS ID");
                }
//                Files.write(path, newLines, StandardCharsets.UTF_8); // Skriver i en fil, ved at bruge dens sti, det der skal stå i den, og et karakterset.
            }
            content.set(line, text);
            Files.write(path, content, StandardCharsets.UTF_8);
        } catch (IOException ioe) { }
    } //Metode til at skrive til en specifik linje i en fil.
    public static void writeToMaster (String data){
        try {
            masterFile.createNewFile(); // laver en ny fil hvis en af samme navn ikke allerede eksisterer. Retunerer en boolean som vi ikke bruger til noget.
            Path path = Paths.get(masterFile.getName());
            List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            boolean alreadyExsits = false;
            int start = 0;
            int end = 0;
            if(data.charAt(0) == 'A'){
                for (int i = 0; i < content.size(); i++){
                    if (content.get(i).charAt(0) == 'a'){
                        start = i;
                        break;
                    }
                }
                for (int i = 0; i < content.size(); i++){
                    if (content.get(i).charAt(0) == 'e'){
                        end = i;
                        break;
                    }
                }
                for (int i = start; i < end; i++){
                    if (content.get(i).equals(data)){
                        alreadyExsits = true;
                        break;
                    }
                }
                if (!alreadyExsits){
                    content.add(start + 1, data);
                }
            } else if(data.charAt(0) == 'C'){
                for (int i = 0; i < content.size(); i++){
                    if (content.get(i).charAt(0) == 'c'){
                        start = i;
                        break;
                    }
                }
                for (int i = 0; i < content.size(); i++){
                    if (content.get(i).charAt(0) == 'a'){
                        end = i;
                        break;
                    }
                }
                for (int i = start; i < end; i++){
                    if (content.get(i).equals(data)){
                        alreadyExsits = true;
                        break;
                    }
                }
                if (!alreadyExsits){
                    content.add(start + 1, data);
                }
            } else if(data.charAt(0) == 'F'){
                for (int i = 0; i < content.size(); i++){
                    if (content.get(i).charAt(0) == 'f'){
                        start = i;
                        break;
                    }
                }
                for (int i = 0; i < content.size(); i++){
                    if (content.get(i).charAt(0) == 'c'){
                        end = i;
                        break;
                    }
                }
                for (int i = start; i < end; i++){
                    if (content.get(i).equals(data)){
                        alreadyExsits = true;
                        break;
                    }
                }
                if (!alreadyExsits){
                    content.add(start +1, data);
                }
            }
            Files.write(path, content, StandardCharsets.UTF_8);
        } catch (IOException ioe) { }
    } //Metode til at skrive til filen der indeholder alle filnavne.

    public static void deleteFile(String filename){
        readingFile = new File(filename + ".csv");
        if (readingFile.delete()){
            System.out.println("Information about " + filename +  " has been deleted.");
            deleteFromMaster(filename);
        }
    }
    public static void deleteFromMaster(String name){

    }

    /* Import sektion - Alt her er relevant for import af data */
    public static void importAll(){
        String readingString; //Den nuværende linje der bliver læst
        try {Scanner masterScanner = new Scanner(masterFile);
            while (masterScanner.hasNextLine()){ // Går igennem masterFile så længe der er flere linjer.
                readingString = masterScanner.nextLine();
                if (readingString.charAt(0) == 'F'){ //Hvis første bogstav på linjen er F er information om en Facilitator
                    readingFile = new File(readingString + ".csv"); //Baseret på linjen finder den en fil til at læse.
                    Scanner readingScanner = new Scanner(readingFile);                    Facilitator newFacilitator = new Facilitator();
                    newFacilitator.setID(readingString.substring(12));
                    newFacilitator.setName(readingScanner.nextLine()); //Information om Events og Arrangementer bliver tilføjet til en faciliatotor gennem Event's ImportData
                } else if(readingString.charAt(0) == 'C'){ //Hvis første bogstav på linjen er C er information om en Customer
                    new Customer(readingString.substring(9)); // Det nye Customer objekt bliver ikke gemt i en attribut/variabel, da vi kun har brug for at oprette den
                    //Information om Events og Arrangementer bliver tilføjet til en customer gennem Event's ImportData
                } else if(readingString.charAt(0) == 'A'){ //Hvis første bogstav på linjen er A er information om et Arrangement
                    int linecount = 0; //Int til at tælle den nuværende linje, bliver brugt til at afgøre ID for Events
                    readingFile = new File(readingString + ".csv"); //Baseret på linjen finder den en fil til at læse.
                    Scanner readingScanner = new Scanner(readingFile);
                    Arrangement newArrangement = new Arrangement();
                    newArrangement.setName(readingString.substring(12));
                    readingString = readingScanner.nextLine();
                    newArrangement.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                    while(readingScanner.hasNextLine()){ // Læser event information mens vi er i Arrangement filen
                        linecount++; // Øger linecount med 1
                        readingString = readingScanner.nextLine();
                        Event newEvent;
                        if(readingString.charAt(0) == 'E'){
                            newEvent = new Excursion(newArrangement);
                            newEvent.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                            newEvent.setID(linecount);
                        } else if(readingString.charAt(0) == 'T'){
                            newEvent = new Transport(newArrangement);
                            newEvent.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                            newEvent.setID(linecount);
                        } else if(readingString.charAt(0) == 'M'){
                            newEvent = new Meeting(newArrangement);
                            newEvent.importData(readingString); //importData i det nye objekt bliver kaldet, hvilket sætter alle variabler op
                            newEvent.setID(linecount);
                        }
                    }
                }
            }
        } catch (FileNotFoundException fnfe) {}

    } //metode til at importere alt data på samme tid.


}