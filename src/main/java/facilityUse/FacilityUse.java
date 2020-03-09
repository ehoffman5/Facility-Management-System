package facilityUse;

import database.DBConnector;
import facilityBase.Facility;
import java.util.*;
import java.sql.*;
import java.time.*;


public class FacilityUse extends Facility {

    // Variable declarations
    private String purposeOfUse;
    private LocalDate startDate;
    private LocalDate endDate;

    // Constructor to initialize class attributes
    public FacilityUse() { }


    // ---------- Getter and Setter Methods ---------- //
    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}