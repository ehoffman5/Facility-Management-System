import java.util.Scanner;
import java.util.*;


public class Facility {
    // Variable declarations
    private int facilityNumber;
    private String facilityName;
    public static HashMap<int, String> facilities;

    Scanner scan = new Scanner(System.in);

    // Constructor to initialize class attributes
    public Facility(facilityNumber, facilityName, facilities) {
        facilityNumber = 0;
        facilityName = "";
        facilities = new HashMap<int, String>();
    }

    // Add new facility
    public void addFacility() {
        System.out.println("Please enter the name of your facility:");
        facilityName = scan.nextLine();

        System.out.println("Please enter the facility number:");
        facilityNumber = scan.nextLine();

        // Add instances of this new facility in the two other user interfaces
        FacilityMaintence.makeNew(facilityNumber);
        FacilityUse.makeNew(facilityNumber);

        facilities.put(facilityNumber, facilityName);
        System.out.println("Thank you, " + name + " has now been added!");
        scanner.close();
    }

    // Remove a facility
    public void removeFacility() {
        facilities.remove(facilityNumber);
    }

    // Print out a list of all current facilities
    public void listFacilities() {
        System.out.println(facilities);
    }

    // Client interface method
    public static void main(String[] args) {
        System.out.println("Welcome to the Facility Manager.  What would you like to do?");
        System.out.println("1) Add a facility.");
        System.out.println("2) Remove a facility.");
        System.out.println("3) List all facilities.");

        String response = scan.nextLine();
        switch(response) {
            case 1:
                addFacility();
                break;
            case 2:
                removeFacility();
                break;
            case 3:
                listFacilities();
                break;
            default:
                "Error, incorrect input.";
                break;
        }

    }

}