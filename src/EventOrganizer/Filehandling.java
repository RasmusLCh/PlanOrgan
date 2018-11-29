package EventOrganizer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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

    /* Import sektion - Alt her er relevant for export af data */
    private File file;
    private Scanner scanner;

    public static void writeToFile(String fileName, String text) {
        try {
            Scanner scanner = new Scanner(new File(fileName + ".csv"));
            while (scanner.hasNextLine()){

            }


/*            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + ".csv"));
            BufferedReader reader = new BufferedReader(new FileReader(fileName + ".csv"));
            while (reader.readLine() != null) {
//                    writer.newLine();
            }
            writer.write(text);
            writer.close();
            reader.close();*/
        } catch (FileNotFoundException fnfe) {} catch (IOException ioe) {}


/*        try {
            PrintWriter writer = new PrintWriter(fileName + ".csv", "UTF-8");
            writer.println(text);
            writer.close();
        } catch (FileNotFoundException fnfe) {} catch (UnsupportedEncodingException uee) {} catch (IOException ioe) {}*/

    }

    public static void writeToLine(String lineName, String text) {

    }

    public void setFile(File file){
        this.file = file;
    }
}