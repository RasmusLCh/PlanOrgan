package EventOrganizer;

import java.time.LocalDateTime;

public class Arrangement implements Readable, Exportable {

    @Override
    public void read() {

    }
    @Override
    public void exportData() {
//        Filehandling.writeToFile(name, startTime.toString() + "," + endTime.toString() + "," + getPrice());
        Filehandling.writeToLine("ARRANGEMENT_" + name,startTime.toString() + "," + endTime.toString() + "," + getPrice(), 0);
    }
    @Override
    public void importData() {
        Menu.setCurrentRead(this);
    }

    private String name = "New Arrangement";
    private float price = 0;
    private LocalDateTime startTime = LocalDateTime.now();
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
        //find filen, og slet den
    }
}