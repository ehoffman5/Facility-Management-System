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



    // Make new facilityBase reservation
    public void assignFacilityToUse(FacilityUse facUse) {

        FacilityUse useObj = new FacilityUse();

        //ensures the start and end data are valid and room isn't already in use at that time
        if (facUse.getStartDate().isAfter(facUse.getEndDate())) {
            System.out.println("Start date must be before end date.");
        } else if (isInUseDuringInterval(facUse)) {
            System.out.println("This room at the facility is already in use during this interval.");
        } else {
            try {
                useObj.assignFacilityToUse(facUse);
            } catch (Exception se) {
                System.err.println("Use: Threw an Exception assigning a facility to use.");
                System.err.println(se.getMessage());
            }
        }

    }

    // Check to see if a facility is in use
    boolean isInUseDuringInterval(FacilityUse facUse) {

        boolean result = false;
        try {
            //Insert the facility number and start/end dates into use table
            Statement st = DBConnector.getConnection().createStatement();
            String selectUseAssignments = "SELECT * FROM FacilityUse WHERE facility_number = " + facUse.getFacilityNumber();

            ResultSet useRS = st.executeQuery(selectUseAssignments);
            System.out.println("Use: *************** Query " + selectUseAssignments + "\n");

            //check if dates in database overlap with input interval
            while (useRS.next()) {
                LocalDate assignStart = useRS.getDate("start_date").toLocalDate();
                LocalDate assignEnd = useRS.getDate("end_date").toLocalDate();
                if (facUse.getStartDate().isBefore(assignEnd) && (assignStart.isBefore(facUse.getEndDate()) ||
                        assignStart.equals(facUse.getEndDate()))) {
                    result = true;
                    break;
                }
            }

            //close to manage resources
            useRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Use: Threw a SQLException checking if "
                    + "facility is in use during an interval.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return result;
    }

}