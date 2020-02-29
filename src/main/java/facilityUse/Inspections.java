package facilityUse;

import java.util.*;

public class Inspections {
    // Variable declarations
    Date inspectionDate;
    String inspectionDetails;

    // Constructor to initialize class attributes
    public Inspections() {
    }


    // ---------- Getter and Setter Methods ---------- //
    public void setInspectionDate(Date inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public Date getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDetails(String inspectionDetails) {
        this.inspectionDetails = inspectionDetails;
    }

    public String getInspectionDetails() {
        return inspectionDetails;
    }


    // Function for showing all assigned inspections
    public void listInspections() {

    }
}
