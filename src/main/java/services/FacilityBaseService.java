package services;

import database.DBConnector;
import facilityBase.Facility;
import facilityBase.FacilityDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacilityBaseService {

    // ---------- Facility Base Methods ---------- //
    // Add new facility
    public void addFacility(Facility facility) {
        Connection con = DBConnector.getConnection();
        PreparedStatement facPst = null;
        PreparedStatement addPst = null;

        try {
            //Insert the Facility object
            String facStm = "INSERT INTO facility(facility_number) VALUES(?)";
            facPst = con.prepareStatement(facStm);
            facPst.setInt(1, facility.getFacilityNumber());
            facPst.executeUpdate();

            //Insert the FacilityDetail object
            String addStm = "INSERT INTO facility_detail(details_id, facility_number, facility_name, phone_number) VALUES(?, ?, ?, ?)";
            addPst = con.prepareStatement(addStm);
            //addPst.setString(1, facility.getFacilityDetails().getName());  // TODO: Automate primary key (details_id)??
            addPst.setInt(2, facility.getFacilityDetails().getFacilityNumber());
            addPst.setString(3, facility.getFacilityDetails().getFacilityName());
            addPst.setString(4, facility.getFacilityDetails().getFacilityPhoneNo());
            addPst.executeUpdate();
        } catch (SQLException ex) {

        } finally {

            try {
                if (addPst != null) {
                    addPst.close();
                    facPst.close();
                }
                if (con != null) {
                    con.close();
                }

            } catch (SQLException ex) {
                System.err.println("Facility: Threw a SQLException saving the facility object.");
                System.err.println(ex.getMessage());
            }
        }
    }

    // Remove a facility
    public void removeFacility(int facilityNumber) {

        try {
            //remove from FacilityUse table
            Statement st = DBConnector.getConnection().createStatement();
            String removeFacilityUseQuery = "delete from FacilityUse where facility_number = '" + facilityNumber + "'";
            st.execute(removeFacilityUseQuery);

            System.out.println("Facility: *************** Query " + removeFacilityUseQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException removing the Facility from FacilityUse table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from Inspections table
            Statement st = DBConnector.getConnection().createStatement();
            String removeInspectionsQuery = "delete from Inspections where facility_number = '" + facilityNumber + "'";
            st.execute(removeInspectionsQuery);

            System.out.println("Facility: *************** Query " + removeInspectionsQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException removing the Facility from Inspections table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from MaintenanceRequests table
            Statement st = DBConnector.getConnection().createStatement();
            String removeMaintenanceRequestsQuery = "delete from MaintenanceRequests where facility_number = '" + facilityNumber + "'";
            st.execute(removeMaintenanceRequestsQuery);

            System.out.println("Facility: *************** Query " + removeMaintenanceRequestsQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException removing the Facility from MaintenanceRequests table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from ActiveMaintenance table
            Statement st = DBConnector.getConnection().createStatement();
            String removeActiveMaintenanceQuery = "delete from ActiveMaintenance where facility_number = '" + facilityNumber + "'";
            st.execute(removeActiveMaintenanceQuery);

            System.out.println("Facility: *************** Query " + removeActiveMaintenanceQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException removing the Facility from ActiveMaintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from FacilityCapacity table
            Statement st = DBConnector.getConnection().createStatement();
            String removeFacilityCapacityQuery = "delete from FacilityCapacity where facility_number = '" + facilityNumber + "'";
            st.execute(removeFacilityCapacityQuery);

            System.out.println("Facility: *************** Query " + removeFacilityCapacityQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException removing the Facility from FacilityCapacity table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from FacilityDetail table
            Statement st = DBConnector.getConnection().createStatement();
            String removeFacilityDetailQuery = "delete from FacilityDetail where facility_number = '" + facilityNumber + "'";
            st.execute(removeFacilityDetailQuery);

            System.out.println("Facility: *************** Query " + removeFacilityDetailQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException removing the Facility Detail from FacilityDetail table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from Facility table
            Statement st = DBConnector.getConnection().createStatement();
            String removeFacilityQuery = "delete from facility where facility_number = '" + facilityNumber + "'";
            st.execute(removeFacilityQuery);

            System.out.println("Facility: *************** Query " + removeFacilityQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException removing the Facility object from Facility table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
    }

    // Print out a list of all current facilities
    public List<Facility> listFacilities() {

        List<Facility> listOfFac = new ArrayList<Facility>();

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String getAllFacilitiesQuery = "SELECT * FROM facility";

            ResultSet facRS = st.executeQuery(getAllFacilitiesQuery);
            System.out.println("Facility: *************** Query " + getAllFacilitiesQuery + "\n");

            Facility fac1 = new Facility();
            while ( facRS.next() ) {
                fac1.setFacilityNumber(facRS.getInt("facility_number"));
                listOfFac.add( getFacilityInformation(fac1.getFacilityNumber()) );
            }

            //close to manage resources
            facRS.close();
            st.close();

            return listOfFac;

        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException retrieving list of facilities.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
        return null;
    }


    // ---------- Facility Details Methods ---------- //
    // Function for reading details
    public void addFacilityDetails(int facilityNumber, String facilityPhoneNo, String facilityName) {
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement facPst = null;
            //Get Facility

            String updateFacilityDetailQuery = "UPDATE FacilityDetails SET phone_number = ? WHERE facility_number = ?";

            facPst = con.prepareStatement(updateFacilityDetailQuery);
            facPst.setString(1, facilityPhoneNo);
            facPst.setInt(2, facilityNumber);
            facPst.executeUpdate();

            System.out.println("Facility: *************** Query " + updateFacilityDetailQuery + "\n");

            //close to manage resources
            facPst.close();
            con.close();

        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException updating the phone number in FacilityDetail table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement facPst = null;
            //Get Facility

            String updateFacilityDetailQuery = "UPDATE FacilityDetails SET facility_name = ? WHERE facility_number = ?";

            facPst = con.prepareStatement(updateFacilityDetailQuery);
            facPst.setString(1, facilityName);
            facPst.setInt(2, facilityNumber);
            facPst.executeUpdate();

            System.out.println("Facility: *************** Query " + updateFacilityDetailQuery + "\n");

            //close to manage resources
            facPst.close();
            con.close();

        }
        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException updating the facility name in FacilityDetail table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
    }

    // Function for listing facility details
    public Facility getFacilityInformation(int facility_number) {

        try {

            Facility fac1 = new Facility();
            fac1.setFacilityNumber(facility_number);

            //Get details about facility
            Statement st = DBConnector.getConnection().createStatement();
            String selectDetailQuery = "SELECT facility_number,facility_name,phone_number FROM FacilityDetails WHERE facility_number = '" + facility_number + "'";
            ResultSet detRS = st.executeQuery(selectDetailQuery);
            FacilityDetails detail = new FacilityDetails();

            System.out.println("Facility: *************** Query " + selectDetailQuery + "\n");

            while ( detRS.next() ) {
                detail.setFacilityNumber(detRS.getInt("facility_number"));
                detail.setFacilityName(detRS.getString("facility_name"));
                detail.setFacilityPhoneNo(detRS.getString("phone_number"));
            }

            fac1.setFacilityDetails(detail);

            //close to manage resources
            detRS.close();

            return fac1;
        }

        catch (SQLException se) {
            System.err.println("Facility: Threw a SQLException retrieving the Facility object.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }
}
