package sqlData;

import database.DBConnector;
import facilityBase.Facility;
import facilityMaintenance.FacilityMaintenance;
import interfaces.FacilityMaintenanceDataImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacilityMaintenanceData implements FacilityMaintenanceDataImpl {

    // Data class separates DB management concerns from service classes

    // ---------- Facility Maintenance Methods ---------- //
    // Method for submitting a new request
    public FacilityMaintenance makeFacilityMaintRequest(Facility fac, String maintenanceDescription, int cost) {
        try {

            FacilityMaintenance maint = new FacilityMaintenance();
            maint.setMaintRequestDescription(maintenanceDescription);
            maint.setCost(cost);
            maint.setFacilityNumber(fac.getFacilityNumber());

            Connection con = DBConnector.getConnection();
            PreparedStatement facPst = null;

            String makeMaintRequestQuery = "INSERT INTO MaintenanceRequests (facility_id, cost, description) VALUES (?,?,?)";
            facPst = con.prepareStatement(makeMaintRequestQuery);
            facPst.setInt(1, fac.getFacilityNumber());
            facPst.setInt(2, cost);
            facPst.setString(3, maintenanceDescription);
            facPst.executeUpdate();
            System.out.println("Maintenance: *************** Query " + makeMaintRequestQuery + "\n");

            //close to manage resources
            con.close();

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
            Connection con = DBConnector.getConnection();
            PreparedStatement facPst = null;
            Statement st = DBConnector.getConnection().createStatement();

            String listMaintRequestsQuery = "SELECT * FROM MaintenanceRequests WHERE facility_number = ? ORDER BY cost";
            facPst = con.prepareStatement(listMaintRequestsQuery);
            facPst.setInt(1, fac.getFacilityNumber());
            facPst.executeUpdate();

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
            con.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException retrieving list of maintenance requests from MaintenanceRequest table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfMaintRequests;
    }

    // Method for setting request to an active order
    public void scheduleMaintenance(FacilityMaintenance maintRequest) {

        Connection con = DBConnector.getConnection();
        PreparedStatement facPst = null;

        try {  // add to ActiveMaintenance

            Statement st = DBConnector.getConnection().createStatement();
            String scheduleMaintenanceAddQuery = "INSERT INTO ActiveMaintenance (facility_number, cost, description) VALUES (?,?,?)";
            facPst = con.prepareStatement(scheduleMaintenanceAddQuery);
            facPst.setInt(1, maintRequest.getFacilityNumber());
            facPst.setDouble(2, maintRequest.getCost());
            facPst.setString(3, maintRequest.getMaintRequestDescription());
            facPst.executeUpdate();

            st.execute(scheduleMaintenanceAddQuery);
            System.out.println("Maintenance: *************** Query " + scheduleMaintenanceAddQuery + "\n");

            //close to manage resources
            con.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException adding a ActiveMaintenance request to maintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {  // remove from MaintenanceRequests

            Statement st = DBConnector.getConnection().createStatement();
            String scheduleMaintenanceRemoveQuery = "DELETE FROM MaintenanceRequests WHERE facility_number = ? AND description = ? AND cost = ?";
            facPst = con.prepareStatement(scheduleMaintenanceRemoveQuery);
            facPst.setInt(1, maintRequest.getFacilityNumber());
            facPst.setString(2, maintRequest.getMaintRequestDescription());
            facPst.setDouble(3, maintRequest.getCost());
            facPst.executeUpdate();

            st.execute(scheduleMaintenanceRemoveQuery);
            System.out.println("Maintenance: *************** Query " + scheduleMaintenanceRemoveQuery + "\n");

            //close to manage resources
            con.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException removing a maintenance request from MaintenanceRequests table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
    }

    // Prints out a list of all active maintenance orders
    public List<FacilityMaintenance> listMaintenance(Facility fac) {
        List<FacilityMaintenance> listOfCompletedMaintenance = new ArrayList<FacilityMaintenance>();
        Connection con = DBConnector.getConnection();
        PreparedStatement facPst = null;

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String listMaintenanceQuery = "SELECT * FROM ActiveMaintenance WHERE facility_number = ? ORDER BY cost";
            facPst = con.prepareStatement(listMaintenanceQuery);
            facPst.setInt(1, fac.getFacilityNumber());
            facPst.executeUpdate();

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
            con.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException retrieving list of maintenance from ActiveMaintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfCompletedMaintenance;

    }


    // ---------- Cost Methods ---------- //
    // Function for calculating the cost of a maintenance request per facilityBase
    public int calcMaintCostForFacility(Facility fac) {

        Connection con = DBConnector.getConnection();
        PreparedStatement facPst = null;

        try {

            int totalCost = 0;

            Statement st = DBConnector.getConnection().createStatement();
            String calcMaintenanceCostQuery = "SELECT SUM(cost) FROM ActiveMaintenance WHERE facility_number = ?";
            facPst = con.prepareStatement(calcMaintenanceCostQuery);
            facPst.setInt(1, fac.getFacilityNumber());
            facPst.executeUpdate();

            ResultSet maintRS = st.executeQuery(calcMaintenanceCostQuery);

            while ( maintRS.next() ) {
                totalCost = maintRS.getInt(1);
            }
            System.out.println("Maintenance: *************** Query " + calcMaintenanceCostQuery + "\n");

            //close to manage resources
            maintRS.close();
            con.close();
            st.close();

            return totalCost;

        }
        catch (SQLException se) {
            System.err.println("Maintenance: Threw a SQLException calculating total maintenance cost from ActiveMaintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return 0;
    }
}
