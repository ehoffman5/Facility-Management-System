package facilityMaintenance;

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
        requests = new HashMap<Integer, String>();
    }

    // Method for submitting a new request
    public void makeFacilityMaintRequest() {
        System.out.println("Please enter the facilityBase number:");
        facilityNumber = scan.nextInt();

        scan.nextLine();
        System.out.println("Please explain the reason for your request:");
        requestDescription = scan.nextLine();

        requests.put(facilityNumber, requestDescription);
        System.out.println("Your submission has been added.  Here is what you typed: " + requestDescription);
    }

    // Print out a list of all current requests
    public void listFacilityMaintRequest() {
        System.out.println(requests);
    }

    // Client interface method
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        FacilityMaintenance myFacilityMaintenance = new FacilityMaintenance();

        System.out.println("Welcome to the Maintenance Manager.  What would you like to do?");
        System.out.println("1) Make a maintenance request.");
        System.out.println("2) List all maintenance requests.");

        int response = myScan.nextInt();
        switch(response) {
            case 1:
                myFacilityMaintenance.makeFacilityMaintRequest();
                break;
            case 2:
                myFacilityMaintenance.listFacilityMaintRequest();
                break;
            default:
                System.out.println("Error, incorrect input.");
                break;
        }
    }

}