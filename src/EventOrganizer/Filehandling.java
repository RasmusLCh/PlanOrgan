package EventOrganizer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
    private static File readingFile;

    public static void setFile(File file){
        readingFile = file;
    }
}