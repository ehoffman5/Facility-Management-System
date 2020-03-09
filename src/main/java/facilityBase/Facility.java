package facilityBase;

import database.DBConnector;
import facilityBase.FacilityDetails;
import facilityBase.FacilityCapacity;
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

}