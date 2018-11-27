import java.util.ArrayList;

public class Filehandling {

    /* Export sektion - Alt her er relevant for export af data */
    static ArrayList<Exportable> allExports = new ArrayList<>(); //Package private
    static ArrayList<Exportable> selectedExports = new ArrayList<>(); //Package private
    public static void autoAddExportable(Exportable exportable){
        allExports.add(exportable);
    }
    public static void addExportable(Exportable exportable){
        selectedExports.add(exportable);
    }
    public static void removeExportable(Exportable exportable){
        selectedExports.remove(exportable);
    }
    public static void exportAll(){
        for (int i = 0; i < allExports.size(); i++){
            allExports.get(i).exportData();
        }
    }
    public static void executeExport(){
        for (int i = 0; i < selectedExports.size(); i++){
            selectedExports.get(i).exportData();
        }
    }

    /* Import sektion - Alt her er relevant for export af data */

}
