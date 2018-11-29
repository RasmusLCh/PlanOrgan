package EventOrganizer;

import java.time.LocalDateTime;

public class Event implements Readable, Exportable {


    @Override
    public void read() { } //Events af en uspecificeret type kan ikke læses
    @Override
    public void exportData() { } //Events af en uspecificeret type kan ikke exporteres
    @Override
    public void importData() { } //Events af en uspecificeret type kan ikke importeres

    private String name = "New Event";
    private String description = "Insert description here";
    private Arrangement arrangement;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Facilitator facilitator;
    private String comment;
    private String location;
    private String customer;

    public String getName(){
        return this.name;
    }
    public void setName(String newName){
        this.name = newName;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String newDescription){
        this.description = newDescription;
    }
    public Arrangement getArrangement() {
        return arrangement;
    }
    public void setArrangement(Arrangement arrangement) {
        this.arrangement = arrangement;
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
    public Facilitator getFacilitator() {
        return facilitator;
    }
    public void setFacilitator(Facilitator facilitator) {
        this.facilitator = facilitator;
        facilitator.addToEventList(this);
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    private float calculatePrice(){
        return 2;
    }
    public void deleteEvent(){
        //find filen, og slet information kun om denne
    }

}
