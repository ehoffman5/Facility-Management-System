package facility;

import java.util.*;


public class Facility {
    // Variable declarations
    private int facilityNumber;
    private String facilityName;
    int size;
    public static HashMap<Integer, String> facilities;

    Scanner scan = new Scanner(System.in);

    // Constructor to initialize class attributes
    public Facility() {
        facilityNumber = 0;
        facilityName = "";
        size = 0;
        facilities = new HashMap<Integer, String>();
    }

    // Add new facility
    public void addFacility() {
        System.out.println("Please enter the name of your facility:");
        facilityName = scan.nextLine();

        System.out.println("Please enter the facility number:");
        facilityNumber = scan.nextInt();

        facilities.put(facilityNumber, facilityName);
        size++;
        System.out.println("Thank you, " + facilityName + " has now been added!");
    }

    // Remove a facility
    public void removeFacility() {
        System.out.println("Please enter the number of the facility you would like to remove:");
        facilityNumber = scan.nextInt();
        facilities.remove(facilityNumber);
        size--;
        System.out.println("facility.Facility number: " + facilityNumber + " has now been removed.");
    }

    // Print out a list of all current facilities
    public void listFacilities() {
        System.out.println(facilities);
    }

    // Client interface method
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        Facility myFacility = new Facility();

        System.out.println("Welcome to the facility.Facility Manager.  What would you like to do?");
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