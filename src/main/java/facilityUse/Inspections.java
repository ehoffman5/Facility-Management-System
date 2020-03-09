package facilityUse;

import database.DBConnector;
import facilityBase.Facility;
import java.util.*;
import java.sql.*;
import java.time.*;

public class Inspections extends FacilityUse {
    // Variable declarations
    LocalDate inspectionDate;
    String inspectionDetails;

    // Constructor to initialize class attributes
    public Inspections() {
    }


    // ---------- Getter and Setter Methods ---------- //
    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDetails(String inspectionDetails) {
        this.inspectionDetails = inspectionDetails;
    }

    public String getInspectionDetails() {
        return inspectionDetails;
    }

}
