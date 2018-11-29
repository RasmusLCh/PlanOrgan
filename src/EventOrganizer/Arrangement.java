package EventOrganizer;

import java.time.LocalDateTime;

public class Arrangement implements Readable, Exportable {

    @Override
    public void read() {

    } //Fra Interface Readable, tillader objektet at blive læst
    @Override
    public void exportData() {
        Filehandling.writeToLine("ARRANGEMENT_" + name,startTime.toString() + "," + endTime.toString() + "," + getPrice(), 0);
    } //Fra Interface Exportable, tillader objektet at blive eksporteret til en Arrangement fil
    @Override
    public void importData() {
        Menu.setCurrentRead(this);
    } //Fra Interface Exportable, tillader objektet at blive læst fra en Arrangement fil

    private String name = "New Arrangement";
    private float price = 0;
    private LocalDateTime startTime = LocalDateTime.now();  //LocalDateTime er en importeret klasse der holder styr på tid.
    private LocalDateTime endTime = LocalDateTime.now();

    public Arrangement() {
        Filehandling.autoAddExportable(this);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getPrice() {
        return price;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void deleteArrangement(){
    }        //finder arrangement filen, og sletter den

}