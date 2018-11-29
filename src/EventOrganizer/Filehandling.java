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
    }
    public static void removeExportable(Exportable exportable){
        selectedExports.remove(exportable);
    }
    public static void executeExport(){
        for (int i = 0; i < selectedExports.size(); i++){
            selectedExports.get(i).exportData();
        }
    }
    public static void autoAddExportable(Exportable exportable){
        allExports.add(exportable);
    }
    public static void exportAll(){
        for (int i = 0; i < allExports.size(); i++){
            allExports.get(i).exportData();
        }
    }

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
                }
                Files.write(path, newLines, StandardCharsets.UTF_8);
            }
            content.set(line, text);
            Files.write(path, content, StandardCharsets.UTF_8);
        } catch (IOException ioe) { }
    }

    /* Import sektion - Alt her er relevant for export af data */
    private static File readingFile;

    public static void setFile(File file){
        readingFile = file;
    }
}