package facilityBase;

import java.util.*;

public class FacilityDetails {

    // Variable declarations
    private String facilityName;
    private String facilityPhoneNo;
    public static ArrayList<String> details;

    // Constructor to initialize class attributes
    public FacilityDetails(ArrayList<String> details) {
        this.details = new ArrayList<String>();
    }


    // ---------- Getter and Setter Methods ---------- //
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityNumber() {
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
    public void addFacilityDetails(String facilityName, String facilityPhoneNo) {
        details.add(facilityName);
        details.add(facilityPhoneNo);
    }

    // Function for modifying details
    public void getFacilityInformation() {
        System.out.println(details);
    }
}
