package services;

import facilityBase.Facility;
import facilityUse.FacilityUse;
import facilityUse.Inspections;
import interfaces.FacilityUseServiceImpl;
import sqlData.FacilityUseData;

import java.util.List;

public class FacilityUseService implements FacilityUseServiceImpl {

    // Service class separates concerns from main classes with getter and setter methods

    private FacilityUseData useData = new FacilityUseData();

    // ---------- FacilityUse Methods ---------- //
    // Make new facilityBase reservation
    public void assignFacilityToUse(FacilityUse facUse) {
        //ensures the start and end data are valid and room isn't already in use at that time
        if (facUse.getStartDate().isAfter(facUse.getEndDate())) {
            System.out.println("Start date must be before end date.");
        } else if (isInUseDuringInterval(facUse)) {
            System.out.println("This room at the facility is already in use during this interval.");
        } else {
            try {
                assignFacilityToUse(facUse);
            } catch (Exception se) {
                System.err.println("Use: Threw an Exception assigning a facility to use.");
                System.err.println(se.getMessage());
            }
        }
    }

    // Check to see if a facility is in use
    public boolean isInUseDuringInterval(FacilityUse facUse) {
        try {
            return useData.isInUseDuringInterval(facUse);
        } catch (Exception se) {
            System.err.println("Use: Threw an Exception retrieving interval determination.");
            System.err.println(se.getMessage());
        }

        return false;
    }


    // ---------- Inspections Methods ---------- //
    // Function for showing all assigned inspections
    public List<Inspections> listInspections(Facility fac) {
        try {
            return useData.listInspections(fac);
        } catch (Exception se) {
            System.err.println("Use: Threw an Exception retrieving list of inspections.");
            System.err.println(se.getMessage());
        }

        return null;
    }


    // ---------- Inspections Methods ---------- //
    // Display how much a facility is used
    public List<FacilityUse> listActualUsage(Facility fac) {
        try {
            return useData.listActualUsage(fac);
        } catch (Exception se) {
            System.err.println("Use: Threw an Exception retrieving list of usage.");
            System.err.println(se.getMessage());
        }

        return null;
    }

    // calculate total rate of facilities currently in use
    public double calcUsageRate(Facility fac) {
        try {
            return useData.calcUsageRate(fac);
        } catch (Exception se) {
            System.err.println("Use: Threw an Exception retrieving usage rate.");
            System.err.println(se.getMessage());
        }

        return 0.0;
    }

    public void vacateFacility(Facility fac) {
        try {
            useData.vacateFacility(fac);
        } catch (Exception se) {
            System.err.println("Use: Threw an Exception vacating facility.");
            System.err.println(se.getMessage());
        }
    }
}
