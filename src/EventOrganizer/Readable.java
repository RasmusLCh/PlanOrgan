package EventOrganizer;

public interface Readable { //Interface der tillader sektretæren at læse information om objekter.
    String returnOptions = "\n 0: Return";
    String exportOptions = "\n 111: Select for Export \n 112: Export only Selected for Export \n 113: Export All";
    void readEditInfo();
}
