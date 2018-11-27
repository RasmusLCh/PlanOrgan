package EventOrganizer;

import java.time.LocalDateTime;

public class Arrangement implements Readable, Exportable {

    @Override
    public void read() {

    }
    @Override
    public void exportData() {
        System.out.println(this + " was exported as a " + getClass());
    }
    @Override
    public void importData() {
        Menu.setCurrentRead(this);
    }

    private String name = "New Arrangement";
    private float price = 0;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

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
