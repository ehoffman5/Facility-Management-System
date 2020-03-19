package clientView;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Scanner;

public class RunClient {
    /***
     * Important! Can only run one client at a time due to connection limitations on Heroku database.
     * Must comment out the other two unused clients in this main method in Client class.
     * @param args args
     * @throws Exception if there is an error creating the particular client
     */

    public static void main(String[] args) throws Exception {

        Scanner scan  = new Scanner(System.in);

        ApplicationContext context = new FileSystemXmlApplicationContext("src/resources/app-context.xml");
        System.out.println("***************** Application Context instantiated! ****************** \n");

        // Instantiate objects of Spring beans to use in activating client UIs
        FacilityBaseUI facilityBaseUI =(FacilityBaseUI) context.getBean("FacilityBaseUI");
        FacilityMaintenanceUI facilityMaintenanceUI =(FacilityMaintenanceUI) context.getBean("FacilityMaintenanceUI");
        FacilityUseUI facilityUseUI =(FacilityUseUI) context.getBean("FacilityUseUI");

        System.out.println("Welcome to the Facility Management System.  Which client interface would you like to use?");
        System.out.println("Type '1' for Facility Interface");
        System.out.println("Type '2' for Facility Maintenance Interface");
        System.out.println("Type '3' for Facility Use Interface");
        int option = scan.nextInt();

        switch(option) {  // choose which interface to use
            case 1:
                facilityBaseUI.runBaseUI();
                break;
            case 2:
                facilityMaintenanceUI.runMaintenanceUI();
                break;
            case 3:
                facilityUseUI.runUseUI();
                break;
            default:
                System.out.println("Error: invalid input");
                break;
        }
    }
}
