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
    // TODO: Customize this to fit the FacilityDetail table
    public void addFacilityDetails(int ID, int phoneNumber) {
        try {
            Connection con = DBConnector.getConnection();
            PreparedStatement facPst = null;
            //Get Facility

            String updateFacilityDetailQuery = "UPDATE facility_detail SET phone = ? WHERE facility_id = ?";

            facPst = con.prepareStatement(updateFacilityDetailQuery);
            facPst.setInt(1, phoneNumber);
            facPst.setInt(2, ID);
            facPst.executeUpdate();

            System.out.println("FacilityDAO: *************** Query " + updateFacilityDetailQuery + "\n");

            //close to manage resources
            facPst.close();
            con.close();

        }
        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw a SQLException updating the phone number in Facility Detail table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }
    }

    // Function for modifying details
    public Facility getFacilityInformation(int facility_number) {

        try {

            Facility fac1 = new Facility();
            fac1.setFacilityNumber(facility_number);

            //Get details about facility
            Statement st = DBConnector.getConnection().createStatement();
            String selectDetailQuery = "SELECT name,facility_id,number_of_rooms,phone FROM facility_detail WHERE facility_id = '" + facility_number + "'";
            ResultSet detRS = st.executeQuery(selectDetailQuery);
            FacilityDetails detail = new FacilityDetails();

            System.out.println("FacilityDAO: *************** Query " + selectDetailQuery + "\n");

            while ( detRS.next() ) {
                detail.setFacilityName(detRS.getString("facility_name"));
                detail.setFacilityNumber(detRS.getInt("facility_number"));
                //detail.setNumberOfRooms(detRS.getInt("number_of_rooms"));
                if (detRS.getString("phone_number") != null) {
                    detail.setFacilityPhoneNo(detRS.getString("phone_number"));
                }
            }

            fac1.setFacilityDetails(detail);

            //close to manage resources
            detRS.close();

            return fac1;
        }

        catch (SQLException se) {
            System.err.println("FacilityDAO: Threw a SQLException retrieving the Facility object.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }
}
