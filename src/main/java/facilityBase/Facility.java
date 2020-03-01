package facilityBase;

import database.DBConnector;

import java.sql.*;
import java.util.*;

public class Facility {

    // Variable declarations
    private int facilityNumber;
    private FacilityDetails facilityDetail;

    // Constructor to initialize class attributes
    public Facility() { }


    // ---------- Getter and Setter Methods ---------- //
    public void setFacilityNumber(int facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    public int getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityDetails(FacilityDetails facilityDetail) {
        this.facilityDetail = facilityDetail;
    }

    public FacilityDetails getFacilityDetails() {
        return facilityDetail;
    }


    // ---------- Functionality Methods ---------- //
    // Add new facility
    // TODO: Automate primary key (details_id)
    public void addFacility(Facility facility) {
        Connection con = DBConnector.getConnection();
        PreparedStatement facPst = null;
        PreparedStatement addPst = null;

        try {
            //Insert the facility object
            String facStm = "INSERT INTO facility(id) VALUES(?)";
            facPst = con.prepareStatement(facStm);
            facPst.setInt(1, facility.getFacilityNumber());
            facPst.executeUpdate();

            //Insert the facility_detail object
            String addStm = "INSERT INTO facility_detail(name, facility_id, number_of_rooms, phone) VALUES(?, ?, ?, ?)";
            addPst = con.prepareStatement(addStm);
            // addPst.setString(1, facility.getFacilityDetails().getName());
            addPst.setInt(2, facility.getFacilityDetails().getFacilityNumber());
            addPst.setString(3, facility.getFacilityDetails().getFacilityName());
            if (facility.getFacilityDetails().getFacilityPhoneNo() != null) {
                addPst.setString(4, facility.getFacilityDetails().getFacilityPhoneNo());
            } else {
                addPst.setNull(4, Types.VARCHAR);
            }
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
                System.err.println("FacilityDAO: Threw a SQLException saving the facility object.");
                System.err.println(ex.getMessage());
            }
        }
    }

    // Remove a facility
    // TODO: Should remove all references from all tables
    public void removeFacility(int facilityNumber) {

        try {
            //remove from use table
            Statement st = DBConnector.getConnection().createStatement();
            String removeFacilityUseQuery = "delete from use where facility_number = '" + facilityNumber + "'";
            st.execute(removeFacilityUseQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityUseQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw a SQLException removing the Facility from Use table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from facility_detail table
            Statement st = DBConnector.getConnection().createStatement();
            String removeFacilityDetailQuery = "delete from facility_detail where facility_number = '" + facilityNumber + "'";
            st.execute(removeFacilityDetailQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityDetailQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw a SQLException removing the Facility Detail from Facility Detail table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        try {
            //remove from facility table
            Statement st = DBConnector.getConnection().createStatement();
            String removeFacilityQuery = "delete from facility where facility_number = '" + facilityNumber + "'";
            st.execute(removeFacilityQuery);

            System.out.println("FacilityDAO: *************** Query " + removeFacilityQuery + "\n");
            //close to manage resources
            st.close();
        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw a SQLException removing the Facility object from Facility table.");
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
            System.out.println("FacilityDAO: *************** Query " + getAllFacilitiesQuery + "\n");

            Facility fac1 = new Facility();
            while ( facRS.next() ) {
                fac1.setFacilityNumber(facRS.getInt("facility_number"));
                // listOfFac.add( getFacilityInformation(fac1.getFacilityNumber()) ); TODO: fix this
            }

            //close to manage resources
            facRS.close();
            st.close();

            return listOfFac;

        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw a SQLException retrieving list of facilities.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
        return null;
    }
}