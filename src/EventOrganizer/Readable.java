package EventOrganizer;

public interface Readable { //Interface der tillader sektretæren at læse information om objekter.
//    String landing = "Do you wish to create or edit a ";

    String returnOptions = "\n 0: Return";
    String exportOptions = "\n 111: Select for Export \n 112: Export Selected \n 113: Export All";
    void read();
}
