package EventOrganizer;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Filehandling {

    /* Export sektion - Alt her er relevant for export af data */
    static ArrayList<Exportable> selectedExports = new ArrayList<>(); //Package private, en liste over udvalgte objekter
    static ArrayList<Exportable> allExports = new ArrayList<>(); //Package private, en liste over alle objekter der er blevet oprettet/redigeret i denne session.

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

    /* Import sektion - Alt her er relevant for export af data */
    private File file;
    private Scanner scanner;

    public void setFile(File file){
        this.file = file;
    }
}