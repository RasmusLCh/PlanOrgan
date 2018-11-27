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

    }

    public Meeting() {
        Filehandling.autoAddExportable(this);
    }


}
