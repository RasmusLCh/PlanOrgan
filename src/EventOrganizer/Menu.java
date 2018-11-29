package EventOrganizer;

import java.io.File;
import java.util.Scanner;

public class Menu {

    private static Readable currentRead;
    private static int intInput;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        Facilitator fc = new Facilitator();
        fc.setID("JANP");
        fc.setName("Janus");
        Arrangement ar = new Arrangement();
        Excursion ex = new Excursion();
        ex.setArrangement(ar);
        Transport tr = new Transport();
        tr.setArrangement(ar);
        fc.addToEventList(ex);
        fc.addToEventList(tr);
        Filehandling.exportAll();
//        Filehandling.writeToFile("Default", "ting");
//        startMenu();
    }

    public static void setCurrentRead(Readable newReadable){
        currentRead = newReadable;
        currentRead.read();
    }

    private static void startMenu(){
        System.out.println("Do you wish to work with \n 1: Arrangements \n 2: Events \n 3: Facilitators");
        intInput = input.nextInt();
        switch (intInput){
            case 1: arrangementMenu();
                break;
            case 2: eventMenu();
                break;
            case 3: facilitatorMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                startMenu();
                break;
        }

    }


    private static void arrangementMenu(){
        System.out.println("Do you wish to create or edit an Arrangement? \n 1: Create \n 2: Edit \n 0: Return");
        intInput = input.nextInt();
        switch (intInput){
            case 1: currentRead = new Arrangement();
                break;
            case 2: // TO DO, liste over arrangement filer;
                break;
            case 0: startMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                arrangementMenu();
                break;
        }

    }


    private static void eventMenu(){
        System.out.println("Do you wish to create or edit an Event? \n 1: Create \n 2: Edit \n 0: Return");
        intInput = input.nextInt();
        switch (intInput){
            case 1: createEvent();
                break;
            case 2: // TO DO, liste over event filer;
                break;
            case 0: startMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                eventMenu();
                break;
        }
    }

    private static void createEvent(){
        System.out.println("What kind of Event do you wish to create? \n 1: Excursion \n 2: Meeting \n 3: Transport \n 0: Return");
        intInput = input.nextInt();
        switch (intInput) {
            case 1: currentRead = new Excursion();
                break;
            case 2: currentRead = new Meeting();
                break;
            case 3: currentRead = new Transport();
                break;
            case 0: eventMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                createEvent();
                break;
        }
    }


    private static void facilitatorMenu(){
        System.out.println("Do you wish to create or edit an Facilitator? \n 1: Create \n 2: Edit \n 0: Return");
        intInput = input.nextInt();
        switch (intInput){
            case 1: currentRead = new Facilitator();
                break;
            case 2: // TO DO, liste over Facilitator filer;
                break;
            case 0: startMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                facilitatorMenu();
                break;
        }
    }
}
