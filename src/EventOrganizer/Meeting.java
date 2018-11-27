package EventOrganizer;

public class Meeting extends Event {

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

    private String[] equipment;

    public Meeting() {
        Filehandling.autoAddExportable(this);
    }

    public String[] getEquipment() {
        return equipment;
    }
    public void setEquipment(String[] tools) {
        this.equipment = tools;
    }

}
