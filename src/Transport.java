public class Transport extends Event {

    @Override
    public void read() {

    }
    @Override
    public void exportData() {
        System.out.println(this + " was exported a " + getClass());
    }
    @Override
    public void importData() {

    }

    public Transport() {
        Filehandling.autoAddExportable(this);
    }

}
