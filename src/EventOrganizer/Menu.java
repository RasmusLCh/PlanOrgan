package EventOrganizer;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static ArrayList<Facilitator> facilitators = new ArrayList<>();
    public static ArrayList<Arrangement> arrangements = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Customer> customers = new ArrayList<>();

    static boolean importDone = false;
    private static int intInput;
    private static String stringInput;
    private static Scanner input = new Scanner(System.in);
    static Arrangement arrangement;

    public static void main(String[] args){
        Filehandling.importAll(); //Programmet starter med at importere alt information, for at at gøre det klar til at blive redigeret.
        importDone = true;
        startMenu();
        return;
    }


    private static void startMenu(){
        System.out.println("What do you wish to work with? \n 1: Arrangements \n 2: Events \n 3: Facilitators \n 4: Customers \n 0: Exit");
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: arrangementMenu();
                break;
            case 2: eventMenu();
                break;
            case 3: facilitatorMenu();
                break;
            case 4: customerMenu();
                break;
            case 0: Filehandling.exportAll();
                return;
            default: System.out.println("Invalid Input, try again");
                startMenu();
                break;
        }

    }


    private static void arrangementMenu(){
        System.out.println("Do you wish to create or edit an Arrangement? \n 1: Create \n 2: Edit \n 0: Return");
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: editArrangement(new Arrangement());
                break;
            case 2: System.out.println("Please type the name of the Arrangement you wish to edit: ");
                stringInput = input.nextLine();
                boolean exists = false;
                for (Arrangement arrangement : arrangements){
                    if(arrangement.getName().equals(stringInput)){
                        exists = true;
                        editArrangement(arrangement);
                        break; // breaker ud af for loopet
                    }
                }
                if(!exists){
                    System.out.println("There appears to be no arrangement with that name. \n");
                    System.out.println("Press enter to return");
                    stringInput = input.nextLine();
                    arrangementMenu();
                }
                break; // breaker ud af casen
            case 0: startMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                arrangementMenu();
                break;
        }

    }

    private static void editArrangement(Arrangement arrangement){
        arrangement.readEditInfo();
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: System.out.println("Current name: " + arrangement.getName());
                System.out.println("New Name: ");
                stringInput = input.nextLine();
                arrangement.setName(stringInput);
                editArrangement(arrangement);
                break;
            case 2: System.out.println("Current Start Time: " + arrangement.getStartTime());
                System.out.println("New Start Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                arrangement.setStartTime(stringInput);
                editArrangement(arrangement);
                break;
            case 3: System.out.println("Current End Time: " + arrangement.getEndTime());
                System.out.println("New End Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                arrangement.setEndTime(stringInput);
                editArrangement(arrangement);
                break;
            case 4: arrangement.getEvents();
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
                editArrangement(arrangement);
                break;
            case 5: arrangement.getCustomers();
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
                editArrangement(arrangement);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Arrangement, and all of its associated events? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                if (intInput == 1){
                    arrangement.deleteArrangement();
                }
                arrangementMenu();
                break;
            case 111: Filehandling.addExportable(arrangement);
                System.out.println("Added to selected Export list");
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
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
        stringInput = input.nextLine();
        switch (intInput){
            case 1: createEvent();
                break;
            case 2: System.out.println("Please type the name of the event you wish to edit: ");
                stringInput = input.nextLine();
                boolean exists = false;
                for (Event eventFromList : events){
                    if(eventFromList.getName().equals(stringInput)){
                        Event event = eventFromList;
                        if(event instanceof Excursion) {
                            exists = true;
                            editExcursion(event);
                            break; // breaker ud af for loopet
                        } else if (event instanceof Transport) {
                            exists = true;
                            editTransport(event);
                            break; // breaker ud af for loopet
                        } else if (event instanceof Meeting) {
                            exists = true;
                            editMeeting(event);
                            break; // breaker ud af for loopet
                        }
                    }
                }
                if (!exists){
                    System.out.println("There appears to be no event with that name. \n");
                    eventMenu();
                }
                break; // breaker ud af casen
            case 0: startMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                eventMenu();
                break;
        }
    }

    private static void createEvent(){
        System.out.println("Please type the name of the Arrangement you wish to create an event under.");
        stringInput = input.nextLine();  // Vi vælger hvilket arrangement eventet skal laves under
        boolean exists = false;
        for (Arrangement arrangementFromList : arrangements){
            if(arrangementFromList.getName().equals(stringInput)){
                exists = true;
                arrangement = arrangementFromList;
                break; // breaker ud af for loopet
            }
        }
        if(!exists) { // Hvis arrangementet ikke eksisterer får brugeren en fejl meddelse, og prøver igen.
            System.out.println("There appears to be no arrangement with that name. \n");
            System.out.println("Press enter to return");
            stringInput = input.nextLine();
            eventMenu();
        } else {
            System.out.println("What kind of Event do you wish to create? \n 1: Excursion \n 2: Meeting \n 3: Transport \n 0: Return");
            intInput = input.nextInt();
            stringInput = input.nextLine();
            switch (intInput) {
                case 1: editExcursion(new Excursion(arrangement));
                    break;
                case 2: editMeeting(new Meeting(arrangement));
                    break;
                case 3: editTransport(new Transport(arrangement));
                    break;
                case 0: eventMenu();
                    break;
                default: System.out.println("Invalid Input, try again");
                    createEvent();
                    break;
        }
        }
    }

    private static void editExcursion(Event event){
        event.readEditInfo();
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: System.out.println("Current ID: " + event.getID());
                System.out.println("New ID (numeric only): ");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                event.setID(intInput);
                editExcursion(event);
                break;
            case 2: System.out.println("Current name: " + event.getName());
                System.out.println("New Name: ");
                stringInput = input.nextLine();
                event.setName(stringInput);
                editExcursion(event);
                break;
            case 3: System.out.println("Current Description: " + event.getDescription());
                System.out.println("New Description: ");
                stringInput = input.nextLine();
                event.setDescription(stringInput);
                editExcursion(event);
                break;
            case 4: System.out.println("Current Start Time: " + event.getStartTime());
                System.out.println("New Start Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                event.setStartTime(stringInput);
                editExcursion(event);
                break;
            case 5: System.out.println("Current End Time: " + event.getEndTime());
                System.out.println("New End Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                event.setEndTime(stringInput);
                editExcursion(event);
                break;
            case 6: System.out.println("Current Facilitator: " + event.getFacilitator());
                System.out.println("New Facilitator: ");
                stringInput = input.nextLine();
                event.setFacilitator(stringInput);
                editExcursion(event);
                break;
            case 7: System.out.println("Current Comment: " + event.getComment());
                System.out.println("New Comment: ");
                stringInput = input.nextLine();
                event.setComment(stringInput);
                editExcursion(event);
                break;
            case 8: System.out.println("Current Location: " + event.getLocation());
                System.out.println("New Location: ");
                stringInput = input.nextLine();
                event.setLocation(stringInput);
                editExcursion(event);
                break;
            case 9: System.out.println("Current Customer: " + event.getCustomer());
                System.out.println("New Customer: ");
                stringInput = input.nextLine();
                event.setCustomer(stringInput);
                editExcursion(event);
                break;
            case 11: System.out.println("Current Destination: " + ((Excursion)event).getDestination());
                System.out.println("New Destination: ");
                stringInput = input.nextLine();
                ((Excursion)event).setDestination(stringInput);
                editExcursion(event);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Event? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                if (intInput == 1){
                    event.deleteEvent();
                }
                eventMenu();
                break;
            case 111: Filehandling.addExportable(event);
                System.out.println("Added to selected Export list");
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
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
        event.readEditInfo();
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: System.out.println("Current ID: " + event.getID());
                System.out.println("New ID (numeric only): ");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                event.setID(intInput);
                editTransport(event);
                break;
            case 2: System.out.println("Current name: " + event.getName());
                System.out.println("New Name: ");
                stringInput = input.nextLine();
                event.setName(stringInput);
                editTransport(event);
                break;
            case 3: System.out.println("Current Description: " + event.getDescription());
                System.out.println("New Description: ");
                stringInput = input.nextLine();
                event.setDescription(stringInput);
                editTransport(event);
                break;
            case 4: System.out.println("Current Start Time: " + event.getStartTime());
                System.out.println("New Start Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                event.setStartTime(stringInput);
                editTransport(event);
                break;
            case 5: System.out.println("Current End Time: " + event.getEndTime());
                System.out.println("New End Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                event.setEndTime(stringInput);
                editTransport(event);
                break;
            case 6: System.out.println("Current Facilitator: " + event.getFacilitator());
                System.out.println("New Facilitator: ");
                stringInput = input.nextLine();
                event.setFacilitator(stringInput);
                editTransport(event);
                break;
            case 7: System.out.println("Current Comment: " + event.getComment());
                System.out.println("New Comment: ");
                stringInput = input.nextLine();
                event.setComment(stringInput);
                editTransport(event);
                break;
            case 8: System.out.println("Current Location: " + event.getLocation());
                System.out.println("New Location: ");
                stringInput = input.nextLine();
                event.setLocation(stringInput);
                editTransport(event);
                break;
            case 9: System.out.println("Current Customer: " + event.getCustomer());
                System.out.println("New Customer: ");
                stringInput = input.nextLine();
                event.setCustomer(stringInput);
                editTransport(event);
                break;
            case 11: System.out.println("Current Destination: " + ((Transport)event).getDestination());
                System.out.println("New Destination: ");
                stringInput = input.nextLine();
                ((Transport)event).setDestination(stringInput);
                editTransport(event);
                break;
            case 12: System.out.println("Current Vehicle: " + ((Transport)event).getVehicle());
                System.out.println("New Vehicle: ");
                stringInput = input.nextLine();
                ((Transport)event).setVehicle(stringInput);
                editTransport(event);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Event? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                if (intInput == 1){
                    event.deleteEvent();
                }
                eventMenu();
                break;
            case 111: Filehandling.addExportable(event);
                System.out.println("Added to selected Export list");
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
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
        event.readEditInfo();
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: System.out.println("Current ID: " + event.getID());
                System.out.println("New ID (numeric only): ");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                event.setID(intInput);
                editMeeting(event);
                break;
            case 2: System.out.println("Current name: " + event.getName());
                System.out.println("New Name: ");
                stringInput = input.nextLine();
                event.setName(stringInput);
                editMeeting(event);
                break;
            case 3: System.out.println("Current Description: " + event.getDescription());
                System.out.println("New Description:");
                stringInput = input.nextLine();
                event.setDescription(stringInput);
                editMeeting(event);
                break;
            case 4: System.out.println("Current Start Time: " + event.getStartTime());
                System.out.println("New Start Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                event.setStartTime(stringInput);
                editMeeting(event);
                break;
            case 5: System.out.println("Current End Time: " + event.getEndTime());
                System.out.println("New End Time Date (please input as yyyy-mm-dd hh:mm:ss): ");
                stringInput = input.nextLine();
                event.setEndTime(stringInput);
                editMeeting(event);
                break;
            case 6: System.out.println("Current Facilitator: " + event.getFacilitator());
                System.out.println("New Facilitator: ");
                stringInput = input.nextLine();
                event.setFacilitator(stringInput);
                editMeeting(event);
                break;
            case 7: System.out.println("Current Comment: " + event.getComment());
                System.out.println("New Comment: ");
                stringInput = input.nextLine();
                event.setComment(stringInput);
                editMeeting(event);
                break;
            case 8: System.out.println("Current Location: " + event.getLocation());
                System.out.println("New Location: ");
                stringInput = input.nextLine();
                event.setLocation(stringInput);
                editMeeting(event);
                break;
            case 9: System.out.println("Current Customer: " + event.getCustomer());
                System.out.println("New Customer: ");
                stringInput = input.nextLine();
                event.setCustomer(stringInput);
                editMeeting(event);
                break;
            case 11: System.out.println("Current Equipment: " + ((Meeting)event).getEquipment());
                System.out.println("New Equipment: ");
                stringInput = input.nextLine();
                ((Meeting)event).setEquipment(stringInput);
                editMeeting(event);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Event? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                if (intInput == 1){
                    event.deleteEvent();
                }
                eventMenu();
                break;
            case 111: Filehandling.addExportable(event);
                System.out.println("Added to selected Export list");
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
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
        stringInput = input.nextLine();
        switch (intInput){
            case 1: editFacilitator(new Facilitator());
                break;
            case 2: System.out.println("Please type the ID of the facilitator you wish to edit: ");
                stringInput = input.nextLine();
                boolean exists = false;
                for (Facilitator facilitator : facilitators){
                    if(facilitator.getID().equals(stringInput)){
                        exists = true;
                        editFacilitator(facilitator);
                        break; // breaker ud af for loopet
                    }
                }
                if (!exists){
                    System.out.println("There appears to be no facilitator with that ID. \n");
                    System.out.println("Press enter to return");
                    stringInput = input.nextLine();
                    facilitatorMenu();
                }
                break; // breaker ud af casen
            case 0: startMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                facilitatorMenu();
                break;
        }
    }

    private static void editFacilitator(Facilitator facilitator){
        facilitator.readEditInfo();
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: System.out.println("Current ID: " + facilitator.getID());
                System.out.println("New ID: ");
                stringInput = input.nextLine();
                facilitator.setID(stringInput);
                editFacilitator(facilitator);
                break;
            case 2: System.out.println("Current name: " + facilitator.getName());
                System.out.println("New Name: ");
                stringInput = input.nextLine();
                facilitator.setName(stringInput);
                editFacilitator(facilitator);
                break;
            case 3: facilitator.getEvents();
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
                editFacilitator(facilitator);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Facilitator? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                if (intInput == 1){
                    facilitator.deleteFacilitator();
                }
                facilitatorMenu();
                break;
            case 111: Filehandling.addExportable(facilitator);
                System.out.println("Added to selected Export list");
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
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


    private static void customerMenu(){
        System.out.println("Do you wish to create or edit a Customer? \n 1: Create \n 2: Edit \n 0: Return");
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: System.out.println("Please input name of new Customer, this cannot be changed later!");
                System.out.println("Name: ");
                stringInput = input.nextLine();
                editCustomer(new Customer(stringInput));
                break;
            case 2: System.out.println("Please type the name of the customer you wish to edit: ");
                stringInput = input.nextLine();
                boolean exists = false;
                for (Customer customer : customers){
                    if(customer.getName().equals(stringInput)){
                        exists = true;
                        editCustomer(customer);
                        break; // breaker ud af for loopet
                    }
                }
                if (!exists) {
                    System.out.println("There appears to be no customer with that name. \n");
                    System.out.println("Press enter to return");
                    stringInput = input.nextLine();
                    customerMenu();
                }
                break; // breaker ud af casen
            case 0: startMenu();
                break;
            default: System.out.println("Invalid Input, try again");
                facilitatorMenu();
                break;
        }
    }

    private static void editCustomer(Customer customer){
        customer.readEditInfo();
        intInput = input.nextInt();
        stringInput = input.nextLine();
        switch (intInput){
            case 1: customer.getEvents();
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
                editCustomer(customer);
                break;
            case 99: System.out.println("Are you sure you wish to delete this Customer? \n 1: Yes \n Default: No");
                intInput = input.nextInt();
                stringInput = input.nextLine();
                if (intInput == 1){
                    customer.deleteCustomer();
                }
                facilitatorMenu();
                break;
            case 111: Filehandling.addExportable(customer);
                System.out.println("Added to selected Export list");
                System.out.println("Press enter to return");
                stringInput = input.nextLine();
                editCustomer(customer);
                break;
            case 112: Filehandling.executeExport();
                startMenu();
                break;
            case 113: Filehandling.exportAll();
                startMenu();
                break;
            case 0:
                customerMenu();
                break;
        }

    }

}
