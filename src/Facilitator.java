public class Facilitator implements Readable, Exportable {

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

    public Facilitator() {
        Filehandling.autoAddExportable(this);
    }

}
