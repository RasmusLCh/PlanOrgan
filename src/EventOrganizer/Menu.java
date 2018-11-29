package EventOrganizer;

import java.util.Scanner;

public class Menu {

    private static Readable currentRead;
    private static int intInput;
    private static String strInput;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
        /*        Arrangement ar = new Arrangement();
        Transport tr = new Transport();
        tr.setID(1);
        tr.setArrangement(ar);
        Facilitator fc = new Facilitator();
        fc.setID("JANP");
        fc.setName("Janus");
        tr.setFacilitator(fc);
        Excursion ex = new Excursion();
        ex.setArrangement(ar);
        fc.addToEventList(ex);*/
//        Filehandling.exportAll();
        startMenu();
        return;
    }

    public static void setCurrentRead(Readable newReadable){
        currentRead = newReadable;
        currentRead.read();
    }

    private static void startMenu(){
        System.out.println("Do you wish to work with \n 1: Arrangements \n 2: Events \n 3: Facilitators \n 0: Exit");
        intInput = input.nextInt();
        switch (intInput){
            case 1: arrangementMenu();
                break;
            case 2: eventMenu();
                break;
            case 3: facilitatorMenu();
                break;
            case 0: return;
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
                editArrangement((Arrangement) currentRead);
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

    private static void editArrangement(Arrangement arrangement){
        System.out.println("Currently editing " + arrangement.getName());
        System.out.println("Currently slated price: " + arrangement.getPrice());
        System.out.println(" 1: Name \n 99: Delete Arrangement \n 0: Return \n 111: Select for Export \n 112: Export Selected \n 113: Export All");
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current name: " + arrangement.getName());
                System.out.println("New Name: ");
                strInput = input.next();
                arrangement.setName(strInput);
                editArrangement(arrangement);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Arrangement, and all of its associated events? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                if (intInput == 1){
                    arrangement.deleteArrangement();
                }
                arrangementMenu();
                break;
            case 111: Filehandling.addExportable((Exportable) currentRead);
                editArrangement(arrangement);
                break;
            case 112: Filehandling.executeExport();
                startMenu();
                break;
            case 113: Filehandling.exportAll();
                startMenu();
                break;

            case 0:
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
        editEvent((Event) currentRead);
    }

    private static void editEvent(Event event){
        System.out.println("Currently editing " + event.getName());
//        System.out.println(" 1: Name \n \n \n 99: Delete Event \n 0: Return \n 111: Select for Export \n 112: Export Selected \n 113: Export All");
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current name: " + event.getName());
                System.out.println("New Name: ");
                strInput = input.next();
                event.setName(strInput);
                editEvent(event);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Event? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                if (intInput == 1){
                    event.deleteEvent();
                }
                eventMenu();
                break;
            case 111: Filehandling.addExportable((Exportable) currentRead);
                editEvent(event);
                break;
            case 112: Filehandling.executeExport();
                startMenu();
                break;
            case 113: Filehandling.exportAll();
                startMenu();
                break;
            case 0:
                eventMenu();
                break;
        }

    }


    private static void facilitatorMenu(){
        System.out.println("Do you wish to create or edit an Facilitator? \n 1: Create \n 2: Edit \n 0: Return");
        intInput = input.nextInt();
        switch (intInput){
            case 1: currentRead = new Facilitator();
                editFacilitator((Facilitator)currentRead);
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

    private static void editFacilitator(Facilitator facilitator){
        System.out.println("Currently editing " + facilitator.getID());
        System.out.println(" 1: ID \n 2: Name \n 99: Delete Facilitator \n 0: Return \n 111: Select for Export \n 112: Export Selected \n 113: Export All");
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current ID: " + facilitator.getID());
                System.out.println("New ID: ");
                strInput = input.next();
                facilitator.setID(strInput);
                editFacilitator(facilitator);
                break;
            case 2: System.out.println("Current name: " + facilitator.getName());
                System.out.println("New Name: ");
                strInput = input.next();
                facilitator.setName(strInput);
                editFacilitator(facilitator);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Facilitator? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                if (intInput == 1){
                    facilitator.deleteFacilitator();
                }
                facilitatorMenu();
                break;
            case 111: Filehandling.addExportable((Exportable) currentRead);
                editFacilitator(facilitator);
                break;
            case 112: Filehandling.executeExport();
                startMenu();
                break;
            case 113: Filehandling.exportAll();
                startMenu();
                break;
            case 0:
                facilitatorMenu();
                break;
        }

    }

}
