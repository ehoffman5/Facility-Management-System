package clientView;

import facilityBase.Facility;
import facilityBase.FacilityDetails;
import facilityMaintenance.FacilityMaintenance;
import services.FacilityMaintenanceService;

import java.util.List;

public class FacilityMaintenanceUI {

    private final FacilityMaintenanceService maintenanceService;

    public FacilityMaintenanceUI(FacilityMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    public void runMaintenanceUI(){

        // TODO: Change these objects to persistence
        //set up facilities for dummy data
        Facility fact1 = new Facility();
        FacilityDetails factDet1 = new FacilityDetails();
        fact1.setFacilityNumber(1);
        fact1.setFacilityDetails(factDet1);

        Facility fact3 = new Facility();
        FacilityDetails factDet3 = new FacilityDetails();
        fact3.setFacilityNumber(3);
        fact3.setFacilityDetails(factDet3);

        Facility fact4 = new Facility();
        FacilityDetails factDet4 = new FacilityDetails();
        fact4.setFacilityNumber(4);
        fact4.setFacilityDetails(factDet4);

        Facility fact7 = new Facility();
        FacilityDetails factDet7 = new FacilityDetails();
        fact7.setFacilityNumber(7);
        fact7.setFacilityDetails(factDet7);


        System.out.println("\nMaintenanceClient: *********** Creating new facility maintenance request *****************");
        FacilityMaintenance maintenance = maintenanceService.makeFacilityMaintRequest(fact1, "testing maintenance", 100);
        System.out.println("\nMaintenanceClient: *********** Maintenance request created *****************");


        System.out.println("\nMaintenanceClient: *********** Scheduling this maintenance request *****************");
        maintenanceService.scheduleMaintenance(maintenance);
        System.out.println("\nMaintenanceClient: *********** Maintenance request scheduled *****************");

        System.out.println("\nMaintenanceClient: *********** Calculate total maintenance cost of a facility *****************");
        int totalCost = maintenanceService.calcMaintCostForFacility(fact1);
        System.out.println("The total cost of maintenance already completed at Facility #" + fact1.getFacilityNumber() + " is $" + totalCost + ".");


        //uses sample data to list maintenance requests, formatted as a table
        System.out.println("\nMaintenanceClient: *********** List current maintenance requests at a facility *****************");
        List<FacilityMaintenance> maintRequestList = maintenanceService.listFacilityMaintRequests(fact3);
        Object[][] requests = new Object[maintRequestList.size() + 1][2];
        requests[0] = new Object[] {"Maintenance Request Details", "Cost"};
        for (int i = 1; i <= maintRequestList.size(); i++) {
            requests[i] = new Object[] {maintRequestList.get(i-1).getMaintRequestDescription(), maintRequestList.get(i-1).getCost()};
        }
        System.out.println("Current maintenance requests at Facility #" + fact3.getFacilityNumber() + ":");
        for (Object[] row : requests) {
            System.out.format("   %-29s%6s\n", row);
        }

        //uses sample data to list completed maintenance, formatted as a table
        System.out.println("\nMaintenanceClient: *********** List maintenance completed at a facility *****************");
        List<FacilityMaintenance> maintenanceList = maintenanceService.listMaintenance(fact3);
        Object[][] maintenanceTable = new Object[maintenanceList.size() + 1][2];
        maintenanceTable[0] = new Object[] {"Maintenance Details", "Cost"};
        for (int i = 1; i <= maintenanceList.size(); i++) {
            maintenanceTable[i] = new Object[] {maintenanceList.get(i-1).getMaintRequestDescription(), maintenanceList.get(i-1).getCost()};
        }
        System.out.println("Maintenance completed at Facility #" + fact3.getFacilityNumber() + ":");
        for (Object[] row : maintenanceTable) {
            System.out.format("   %-30s%6s\n", row);
        }

        //uses sample data to list facility problems, formatted as a table
        System.out.println("\nMaintenanceClient: *********** List all problems that have affected a facility *****************");
        List<FacilityMaintenance> facilityProblemsList = maintenanceService.listFacilityProblems(fact3);
        Object[][] problems = new Object[facilityProblemsList.size() + 1][2];
        problems[0] = new Object[] {"Problem Details", "Cost"};
        for (int i = 1; i <= facilityProblemsList.size(); i++) {
            problems[i] = new Object[] {facilityProblemsList.get(i-1).getMaintRequestDescription(), facilityProblemsList.get(i-1).getCost()};
        }
        System.out.println("Problems at Facility #" + fact3.getFacilityNumber() + ":");
        for (Object[] row : problems) {
            System.out.format("   %-30s%6s\n", row);
        }

        System.out.println("\nMaintenanceClient: *********** Calculate the down time for a facility *****************");
        int downTime = maintenanceService.calcDownTimeForFacility(fact3);
        System.out.println("Facility #" + fact3.getFacilityNumber() + " was down for maintenance for " + downTime + " days total, "
                + "assuming each completed maintenance request took 7 days to complete.");

        System.out.println("\nMaintenanceClient: *********** Calculate the problem rate for a facility *****************");
        double problemRate = maintenanceService.calcProblemRateForFacility(fact3) * 100;
        System.out.print("\nThe problem rate at Facility #" + fact3.getFacilityNumber() + " is ");
        System.out.format("%.2f", problemRate);
        System.out.print("%.");

    }
}
