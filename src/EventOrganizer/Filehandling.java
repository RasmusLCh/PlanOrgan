package EventOrganizer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filehandling {

    /* Export sektion - Alt her er relevant for export af data */
    private static ArrayList<Exportable> selectedExports = new ArrayList<>(); //Package private, en liste over udvalgte objekter
    private static ArrayList<Exportable> allExports = new ArrayList<>(); //Package private, en liste over alle objekter der er blevet oprettet/redigeret i denne session.

    public static void addExportable(Exportable exportable){
        selectedExports.add(exportable);
    } //metode der tilføjer et objekt til den selektive liste.
    public static void removeExportable(Exportable exportable){
        selectedExports.remove(exportable);
    } //metode der fjerner et objekt fra den selektive liste.
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

    public static void writeToLine(String fileName, String text, int line) {
        readingFile = new File(fileName + ".csv");
        try {
            readingFile.createNewFile();
            Path path = Paths.get(fileName + ".csv");
            List<String> content = new ArrayList<>(Files.readAllLines(path, StandardCharsets.UTF_8));
            if(content.size() < line + 1){
                List<String > newLines = new ArrayList<>();
                newLines.add("EMPTY SPACE");
                for (int i = 0; i <= line; i++){
                    newLines.add("EMPTY SPACE");
                    content.add("EMPTY SPACE");
                }
                Files.write(path, newLines, StandardCharsets.UTF_8);
            }
            content.set(line, text);
            Files.write(path, content, StandardCharsets.UTF_8);
        } catch (IOException ioe) { }
    } //Metode til at skrive til en specifik linje i en fil.

    /* Import sektion - Alt her er relevant for export af data */
    private static File masterFile = new File ("files.txt");
    private static File readingFile;
    private static String readingString;


    public static void setFile(File file){
        masterFile = file;
    }

    public static void importAll(){
        DateTimeFormatter dTFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try { Scanner masterScanner = new Scanner(masterFile);
            while (masterScanner.hasNextLine()){
                readingString = masterScanner.nextLine();
                readingFile = new File(readingString + ".csv");
                Scanner readingScanner = new Scanner(readingFile);
                if (readingString.charAt(0) == 'F'){
                    Facilitator tempFacilitator = new Facilitator();
                    Menu.facilitators.add(tempFacilitator);
                    tempFacilitator.setID(readingString.substring(12));
                    tempFacilitator.setName(readingScanner.nextLine());
                } else if(readingString.charAt(0) == 'A'){
                    Arrangement tempArrangement = new Arrangement();
                    Menu.arrangements.add(tempArrangement);
                    tempArrangement.setName(readingString.substring(12));
                    readingString = readingScanner.nextLine();
                    String[] times = readingString.split(",");
                    times[0] = times[0].replace('T', ' ');
                    times[1] = times[1].replace('T', ' ');
                    tempArrangement.setStartTime(LocalDateTime.parse(times[0].substring(0, 19), dTFormat));
                    tempArrangement.setEndTime(LocalDateTime.parse(times[1].substring(0, 19) , dTFormat));
                    while(readingScanner.hasNextLine()){

                    }



                } else if(readingString.charAt(0) == 'C'){}


            }
        } catch (FileNotFoundException fnfe) {}

    }


//    public void listF

}