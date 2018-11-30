package EventOrganizer;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static ArrayList<Facilitator> facilitators = new ArrayList<>();
    public static ArrayList<Arrangement> arrangements = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();

    private static Readable currentRead;
    private static int intInput;
    private static String stringInput;
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args){
//       Filehandling.importAll();

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

    private static void createMenu(){
        // Operette eller redigere
    }

    private static void editMenu (){
        // redigere
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
        currentRead.read();
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current name: " + arrangement.getName());
                System.out.println("New Name: ");
                stringInput = input.next();
                arrangement.setName(stringInput);
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
        // ARRANGEMENT SELECTION ?
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
            editExcursion((Excursion)currentRead);
                break;
            case 2: currentRead = new Meeting();
            editMeeting((Meeting)currentRead);
                break;
            case 3: currentRead = new Transport();
            editTransport((Transport)currentRead);
                break;
            case 0: eventMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                createEvent();
                break;
        }
    }

    private static void editExcursion(Event event){
        currentRead.read();
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current ID: " + event.getID());
                System.out.println("New ID: ");
                intInput = input.nextInt();
                event.setID(intInput);
                editExcursion(event);
                break;
            case 2: System.out.println("Current name: " + event.getName());
                System.out.println("New Name: ");
                stringInput = input.next();
                event.setName(stringInput);
                editExcursion(event);
                break;
            case 3: System.out.println("Current Description: " + event.getDescription());
                System.out.println("New Description: ");
                stringInput = input.next();
                event.setDescription(stringInput);
                editExcursion(event);
                break;
            case 4: System.out.println("Current Start Time: " + event.getStartTime());
                System.out.println("New Start Time: ");
//                stringInput = input.next();
//                event.setStartTime();
                editExcursion(event);
                break;
            case 5: System.out.println("Current End Time: " + event.getEndTime());
                System.out.println("New End Time: ");
//                stringInput = input.next();
//                event.setEndTime(stringInput);
                editExcursion(event);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Event? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                if (intInput == 1){
                    event.deleteEvent();
                }
                eventMenu();
                break;
            case 111: Filehandling.addExportable((Exportable) currentRead);
                editExcursion(event);
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

    private static void editTransport(Event event){
        currentRead.read();
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current name: " + event.getName());
                System.out.println("New Name: ");
                stringInput = input.next();
                event.setName(stringInput);
                editTransport(event);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Event? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                if (intInput == 1){
                    event.deleteEvent();
                }
                eventMenu();
                break;
            case 111: Filehandling.addExportable((Exportable) currentRead);
                editTransport(event);
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

    private static void editMeeting(Event event){
        currentRead.read();
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current name: " + event.getName());
                System.out.println("New Name: ");
                stringInput = input.next();
                event.setName(stringInput);
                editMeeting(event);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Event? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                if (intInput == 1){
                    event.deleteEvent();
                }
                eventMenu();
                break;
            case 111: Filehandling.addExportable((Exportable) currentRead);
                editMeeting(event);
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
        System.out.println("Do you wish to create or edit a Facilitator? \n 1: Create \n 2: Edit \n 0: Return");
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
        currentRead.read();
        intInput = input.nextInt();
        switch (intInput){
            case 1: System.out.println("Current ID: " + facilitator.getID());
                System.out.println("New ID: ");
                stringInput = input.next();
                facilitator.setID(stringInput);
                editFacilitator(facilitator);
                break;
            case 2: System.out.println("Current name: " + facilitator.getName());
                System.out.println("New Name: ");
                stringInput = input.next();
                facilitator.setName(stringInput);
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
