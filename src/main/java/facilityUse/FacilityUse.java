package facilityUse;

import java.util.*;


public class FacilityUse {

    // Variable declarations
    private String purposeOfUse;
    private String startDate;
    private String endDate;

    // Constructor to initialize class attributes
    public FacilityUse() {
    }


    // ---------- Getter and Setter Methods ---------- //
    public void setPurposeOfUse(String purposeOfUse) {
        this.purposeOfUse = purposeOfUse;
    }

    public String getPurposeOfUse() {
        return purposeOfUse;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }



    // Make new facilityBase reservation
    public void assignFacilityToUse() {
    }

    // Print out a list of all current reservations
    public void listReservations() {
        System.out.println();
    }

    // Check to see if a facility is in use
    public void isInUseDuringInterval() {
    }

}