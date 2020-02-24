package facilityUse;

import java.util.*;


public class FacilityUse {
    // Variable declarations
    private int facilityNumber;
    private String purposeOfUse;
    public static HashMap<Integer, String> reservations;

    Scanner scan = new Scanner(System.in);

    // Constructor to initialize class attributes
    public FacilityUse() {
        facilityNumber = 0;
        purposeOfUse = "";
        reservations = new HashMap<Integer, String>();
    }

    // Make new facility reservation
    public void assignFacilityToUse() {
        System.out.println("Please enter the facility number:");
        facilityNumber = scan.nextInt();

        scan.nextLine();
        System.out.println("Please explain the purpose of your reservation:");
        purposeOfUse = scan.nextLine();

        reservations.put(facilityNumber, purposeOfUse);
        System.out.println("Your submission has been added.  Here is what you typed: " + purposeOfUse);
    }

    // Print out a list of all current reservations
    public void listReservations() {
        System.out.println(reservations);
    }

    // Client interface method
    public static void main(String[] args) {
        Scanner myScan = new Scanner(System.in);
        FacilityUse myFacilityUse = new FacilityUse();

        System.out.println("Welcome to the Reservation Manager.  What would you like to do?");
        System.out.println("1) Make a reservation.");
        System.out.println("2) List all reservations.");

        int response = myScan.nextInt();
        switch(response) {
            case 1:
                myFacilityUse.assignFacilityToUse();
                break;
            case 2:
                myFacilityUse.listReservations();
                break;
            default:
                System.out.println("Error, incorrect input.");
                break;
        }
    }

}