import java.util.*;


public class FacilityMaintenance {
    // Variable declarations
    private int facilityNumber;
    private String requestDescription;
    public static HashMap<Integer, String> requests;

    Scanner scan = new Scanner(System.in);

    // Constructor to initialize class attributes
    public FacilityMaintenance() {
        facilityNumber = 0;
        requestDescription = "";
        facilities = new HashMap<Integer, String>();
    }

    // Add new facility
    public void addFacility() {
        System.out.println("Please enter the name of your facility:");
        requestDescription = scan.nextLine();

        System.out.println("Please enter the facility number:");
        facilityNumber = scan.nextInt();

        // Add instances of this new facility in the two other user interfaces
        //FacilityMaintence.makeNew(facilityNumber);
        //FacilityUse.makeNew(facilityNumber);

        facilities.put(facilityNumber, requestDescription);
        System.out.println("Thank you, " + requestDescription + " has now been added!");
    }

    // Remove a facility
    public void removeFacility() {
        System.out.println("Please enter the number of the facility you would like to remove:");
        facilityNumber = scan.nextInt();
        facilities.remove(facilityNumber);
        System.out.println("Facility number: " + facilityNumber + " has now been removed.");
    }

    // Print out a list of all current facilities
    public void listFacilities() {
        System.out.println(facilities);
    }

    // Client interface method
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        Facility myFacility = new Facility();

        System.out.println("Welcome to the Facility Manager.  What would you like to do?");
        System.out.println("1) Add a facility.");
        System.out.println("2) Remove a facility.");
        System.out.println("3) List all facilities.");

        int response = myScan.nextInt();
        switch(response) {
            case 1:
                myFacility.addFacility();
                break;
            case 2:
                myFacility.removeFacility();
                break;
            case 3:
                myFacility.listFacilities();
                break;
            default:
                System.out.println("Error, incorrect input.");
                break;
        }
    }

}