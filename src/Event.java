public class Event implements Readable, Exportable {

    private String name = "New Event";
    private String description = "Insert description here";

    @Override
    public void read() { } //Events af en uspecificeret type kan ikke læses
    @Override
    public void exportData() { } //Events af en uspecificeret type kan ikke exporteres
    @Override
    public void importData() { } //Events af en uspecificeret type kan ikke importeres


    public static void createEvent(Event type){ //En static metode, som vi bruger til at oprette nye events, af en valgt type.
        System.out.println("Which type of event would you like to create?");
        System.out.println("1: ");

    }

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

}
