package clientView;

import java.util.Scanner;

public class RunClient {
    /***
     * Important! Can only run one client at a time due to connection limitations on Heroku database.
     * Must comment out the other two unused clients in this main method in Client class.
     * @param args args
     * @throws Exception if there is an error creating the particular client
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) throws Exception {

        Scanner scan  = new Scanner(System.in);

        System.out.println("Welcome to the Facility Management System.  Which client interface would you like to use?");
        System.out.println("Type '1' for Facility Interface");
        System.out.println("Type '2' for Facility Maintenance Interface");
        System.out.println("Type '3' for Facility Use Interface");
        int option = scan.nextInt();

        switch(option) {  // choose which interface to use
            case 1:
                FacilityBaseUI facilityClient = new FacilityBaseUI();
                break;
            case 2:
                FacilityUseUI useClient = new FacilityUseUI();
                break;
            case 3:
                FacilityMaintenanceUI maintenanceClient = new FacilityMaintenanceUI();
                break;
            default:
                System.out.println("Error: invalid input");
                break;
        }
    }
}
