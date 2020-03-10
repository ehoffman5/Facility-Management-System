package services;

import facilityBase.Facility;
import facilityMaintenance.FacilityMaintenance;
import facilityUse.FacilityUse;
import interfaces.FacilityMaintenanceServiceImpl;
import sqlData.FacilityMaintenanceData;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FacilityMaintenanceService implements FacilityMaintenanceServiceImpl {

    // Service class separates concerns from main classes with getter and setter methods

    private FacilityMaintenanceData maintenanceData = new FacilityMaintenanceData();

    // ---------- Facility Maintenance Methods ---------- //
    // Method for submitting a new request
    public FacilityMaintenance makeFacilityMaintRequest(Facility fac, String maintenanceDescription, int cost) {
        try {
            return maintenanceData.makeFacilityMaintRequest(fac, maintenanceDescription, cost);
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception making a maintenance request.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    // Print out a list of all current requests
    public List<FacilityMaintenance> listFacilityMaintRequests(Facility fac) {
        try {
            return maintenanceData.listFacilityMaintRequests(fac);
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception listing maintenance requests.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    // Method for setting request to an active order
    public void scheduleMaintenance(FacilityMaintenance maintRequest) {
        try {
            maintenanceData.scheduleMaintenance(maintRequest);
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception scheduling maintenance.");
            System.err.println(se.getMessage());
        }
    }

    // Prints out a list of all active maintenance orders
    public List<FacilityMaintenance> listMaintenance(Facility fac) {
        try {
            return maintenanceData.listMaintenance(fac);
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception listing completed maintenance.");
            System.err.println(se.getMessage());
        }
        return null;
    }


    // ---------- Cost Methods ---------- //
    // Function for calculating the cost of a maintenance request per facilityBase
    public int calcMaintCostForFacility(Facility fac) {
        try {
            return maintenanceData.calcMaintCostForFacility(fac);
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception calculating maintenance cost for facility.");
            System.err.println(se.getMessage());
        }
        return 0;
    }


    // ---------- DownTime Methods ---------- //
    // Function for calculating time not spent on maintenance requests
    public int calcDownTimeForFacility(Facility fac) {
        int daysOfDownTime = 0;
        try {
            int numberOfCompletedMaintItems = listMaintenance(fac).size();
            daysOfDownTime = numberOfCompletedMaintItems * 7;  // each maintenance order counts for 7 days
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception calculating the down time for a facility.");
            System.err.println(se.getMessage());
        }

        return daysOfDownTime;
    }


    // ---------- Problems Methods ---------- //
    // Function for calculating the ratio of problems a specific facility causes
    public double calcProblemRateForFacility(Facility fac) {
        FacilityUse useService = new FacilityUse();

        try {
            LocalDate facilityStartDate = useService.getStartDate();
            double totalDays = ChronoUnit.DAYS.between(facilityStartDate, LocalDate.now());
            return calcDownTimeForFacility(fac) / totalDays;
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception calculating the problem rate for a facility.");
            System.err.println(se.getMessage());
        }

        return 0;
    }

    // Function to list all problem requests
    public List<FacilityMaintenance> listFacilityProblems(Facility fac) {
        List<FacilityMaintenance> facilityProblems = new ArrayList<FacilityMaintenance>();

        try {
            facilityProblems.addAll(listFacilityMaintRequests(fac));
            facilityProblems.addAll(listMaintenance(fac));

            //sort problems by cost
            Collections.sort(facilityProblems, new Comparator<FacilityMaintenance>() {
                @Override
                public int compare(FacilityMaintenance m1, FacilityMaintenance m2) {
                    return (m2.getCost() > m1.getCost()) ? -1 : 1;
                }
            });

            return facilityProblems;
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception listing all facility problems.");
            System.err.println(se.getMessage());
        }
        return null;
    }
}
