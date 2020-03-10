package sqlData;

import database.DBConnector;
import facilityBase.Facility;
import facilityMaintenance.FacilityMaintenance;
import facilityUse.FacilityUse;
import interfaces.FacilityMaintenanceImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FacilityMaintenanceData implements FacilityMaintenanceImpl {

    // ---------- Facility Maintenance Methods ---------- //
    // Method for submitting a new request
    public FacilityMaintenance makeFacilityMaintRequest(Facility fac, String maintenanceDescription, int cost) {
        try {

            FacilityMaintenance maint = new FacilityMaintenance();
            maint.setMaintRequestDescription(maintenanceDescription);
            maint.setCost(cost);
            maint.setFacilityNumber(fac.getFacilityNumber());

            Statement st = DBConnector.getConnection().createStatement();
            String makeMaintRequestQuery = "INSERT INTO MaintenanceRequests (facility_id, cost, description) VALUES (" +
                    fac.getFacilityNumber() + ", '" + cost + "', " + maintenanceDescription + ")";
            st.execute(makeMaintRequestQuery);
            System.out.println("Maintenance: *************** Query " + makeMaintRequestQuery + "\n");

            //close to manage resources
            st.close();

            return maint;

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException making a maintenance request.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }

    // Print out a list of all current requests
    public List<FacilityMaintenance> listFacilityMaintRequests(Facility fac) {

        List<FacilityMaintenance> listOfMaintRequests = new ArrayList<FacilityMaintenance>();

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String listMaintRequestsQuery = "SELECT * FROM MaintenanceRequests WHERE facility_number = '" +
                    fac.getFacilityNumber() + "' ORDER BY cost";

            ResultSet maintRS = st.executeQuery(listMaintRequestsQuery);
            System.out.println("Maintenance: *************** Query " + listMaintRequestsQuery + "\n");

            while ( maintRS.next() ) {
                FacilityMaintenance maintenanceRequest = new FacilityMaintenance();
                maintenanceRequest.setMaintRequestDescription(maintRS.getString("description"));
                maintenanceRequest.setCost(maintRS.getInt("cost"));
                maintenanceRequest.setFacilityNumber(fac.getFacilityNumber());
                listOfMaintRequests.add(maintenanceRequest);
            }

            //close to manage resources
            maintRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException retrieving list of maintenance "
                    + "requests from MaintenanceRequest table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfMaintRequests;
    }

    // Method for setting request to an active order
    public void scheduleMaintenance(FacilityMaintenance maintRequest) {

        try {  // add to ActiveMaintenance

            Statement st = DBConnector.getConnection().createStatement();
            String scheduleMaintenanceAddQuery = "INSERT INTO ActiveMaintenance (facility_number, cost, description) VALUES (" +
                    maintRequest.getFacilityNumber() + ", '" + maintRequest.getCost() +
                    "', " + maintRequest.getMaintRequestDescription() + ")";
            st.execute(scheduleMaintenanceAddQuery);
            System.out.println("Maintenance: *************** Query " + scheduleMaintenanceAddQuery + "\n");

            //close to manage resources
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException adding a ActiveMaintenance "
                    + "request to maintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {  // remove from MaintenanceRequests

            Statement st = DBConnector.getConnection().createStatement();
            String scheduleMaintenanceRemoveQuery = "DELETE FROM MaintenanceRequests WHERE facility_number = " +
                    maintRequest.getFacilityNumber() + " AND description = '" + maintRequest.getMaintRequestDescription() +
                    "' AND cost = " + maintRequest.getCost();
            st.execute(scheduleMaintenanceRemoveQuery);
            System.out.println("Maintenance: *************** Query " + scheduleMaintenanceRemoveQuery + "\n");

            //close to manage resources
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException removing a "
                    + "maintenance request from MaintenanceRequests table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
    }

    // Prints out a list of all active maintenance orders
    public List<FacilityMaintenance> listMaintenance(Facility fac) {
        List<FacilityMaintenance> listOfCompletedMaintenance = new ArrayList<FacilityMaintenance>();

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String listMaintenanceQuery = "SELECT * FROM ActiveMaintenance WHERE facility_number = '" +
                    fac.getFacilityNumber() + "' ORDER BY cost";

            ResultSet maintRS = st.executeQuery(listMaintenanceQuery);
            System.out.println("Maintenance: *************** Query " + listMaintenanceQuery + "\n");

            while ( maintRS.next() ) {
                FacilityMaintenance maintenance = new FacilityMaintenance();
                maintenance.setMaintRequestDescription(maintRS.getString("description"));
                maintenance.setCost(maintRS.getInt("cost"));
                maintenance.setFacilityNumber(fac.getFacilityNumber());
                listOfCompletedMaintenance.add(maintenance);
            }

            //close to manage resources
            maintRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException retrieving list of maintenance "
                    + "from ActiveMaintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfCompletedMaintenance;

    }


    // ---------- Cost Methods ---------- //
    // Function for calculating the cost of a maintenance request per facilityBase
    public int calcMaintCostForFacility(Facility fac) {

        try {

            int totalCost = 0;

            Statement st = DBConnector.getConnection().createStatement();
            String calcMaintenanceCostQuery = "SELECT SUM(cost) FROM ActiveMaintenance "
                    + "WHERE facility_number = " + fac.getFacilityNumber();
            ResultSet maintRS = st.executeQuery(calcMaintenanceCostQuery);

            while ( maintRS.next() ) {
                totalCost = maintRS.getInt(1);
            }
            System.out.println("Maintenance: *************** Query " + calcMaintenanceCostQuery + "\n");

            //close to manage resources
            maintRS.close();
            st.close();

            return totalCost;

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException calculating total "
                    + "maintenance cost from ActiveMaintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
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
            System.err.println("Maintenance: Threw an Exception calculating "
                    + "the down time for a facility.");
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
            System.err.println("Maintenance: Threw an Exception calculating "
                    + "the down time for a facility.");
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
            System.err.println("Maintenance: Threw an Exception "
                    + "listing all facility problems.");
            System.err.println(se.getMessage());
        }
        return null;
    }
}
