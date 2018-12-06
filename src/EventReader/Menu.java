package EventReader;

import EventOrganizer.*; // Importerer vores egne klasser fra EventOrganizer

import java.util.Scanner;

public class Menu {

    private static String stringInput;
    private static Scanner input = new Scanner(System.in);

    public static void main (String[] args){
        validate();
    }

    private static void validate (){
        System.out.println("Please input ID of facilitator you want to check:");
        stringInput = input.nextLine();
        if(Filehandling.findFacilitator(stringInput)){
            Filehandling.importData("FACILITATOR_" + stringInput);
        } else {
            System.out.println("Facilitator could not be found, try again.");
            validate();
        }
    }

    public static void readFacilitator(Facilitator facilitator){
        facilitator.getEvents();
        System.out.println("Type 'EXIT' to exit program");
        System.out.println("Which event would you like to read? (name only)");
        stringInput = input.nextLine();
        if(stringInput.equals("EXIT")){
            System.exit(0);
        } else
        for (Event event : facilitator.listOfEvents){
            if (event.getName().equals(stringInput)){
                readEventInfo(event, facilitator);
                break;
            }
        }
        System.out.println("Invalid input, try again");
        readFacilitator(facilitator);
    }
    public static void readEventInfo(Event event, Facilitator facilitator){
        System.out.println("Name: " + event.getName());
        System.out.println("Price: " + event.calculatePrice());
        System.out.println("Description: " + event.getDescription());
        System.out.println("Start Time: " + event.getStartTime());
        System.out.println("End Time: " + event.getEndTime());
        System.out.println("Comment: " + event.getComment());
        System.out.println("Customer: " + event.getCustomer());
        System.out.println("Location: " + event.getLocation());
        if(event instanceof Excursion) {
            System.out.println("Destination: " + ((Excursion) event).getDestination());
        } else if(event instanceof Meeting) {
            System.out.println("Equipment: " + ((Meeting) event).getEquipment());
        } else if(event instanceof Transport) {
            System.out.println("Destination: " + ((Transport) event).getDestination());
            System.out.println("Vehicle: " + ((Transport) event).getVehicle());
        }
        System.out.println("Press enter to return");
        stringInput = input.nextLine();
        readFacilitator(facilitator);
    }

}
