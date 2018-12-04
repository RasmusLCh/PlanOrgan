package EventOrganizer;

public interface Exportable { //Interface med 2 metoder, brugt til at importere og eksportere med filer.
    void exportData();
    void importData(String data); // En enkel string kan indeholde alt relevant data for et enkelt objekt
}
