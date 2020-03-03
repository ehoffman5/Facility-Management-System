package facilityBase;

import database.DBConnector;

import java.sql.*;
import java.util.*;

public class FacilityDetails extends Facility {

    // Variable declarations
    private String facilityName;
    private String facilityPhoneNo;

    // Constructor to initialize class attributes
    public FacilityDetails() { }


    // ---------- Getter and Setter Methods ---------- //
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityPhoneNo(String facilityPhoneNo) {
        this.facilityPhoneNo = facilityPhoneNo;
    }

    public String getFacilityPhoneNo() {
        return facilityPhoneNo;
    }

    // ---------- CRUD Methods ---------- //
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
