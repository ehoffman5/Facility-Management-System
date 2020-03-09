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

}
