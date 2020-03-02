package facilityMaintenance;

import facilityBase.Facility;
import java.util.*;
import java.sql.*;

public class Problems extends FacilityMaintenance {
/*
    // Function for calculating the ratio of problems a specific facility causes
    public double calcProblemRateForFacility(Facility fac) {
        UseService useService = new UseService();
        try {
            LocalDate facilityStartDate = useService.getFacilityStartDate(fac);
            double totalDays = ChronoUnit.DAYS.between(facilityStartDate, LocalDate.now());
            return calcDownTimeForFacility(fac) / totalDays;
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception calculating "
                    + "the down time for a facility.");
            System.err.println(se.getMessage());
        }

        return 0;
    }
    }

    // Function to list all problem requests
    public List<FacilityMaintenance> listFacilityProblems(Facility fac) {
        List<FacilityMaintenance> facilityProblems = new ArrayList<FacilityMaintenance>();
        try {
            facilityProblems.addAll(FacilityMaintenance.listFacilityMaintRequests(fac));  // TODO: fix this
            facilityProblems.addAll(FacilityMaintenance.listMaintenance(fac));

            //sort problems by cost
            Collections.sort(facilityProblems, new Comparator<FacilityMaintenance>() {
                @Override
                public int compare(FacilityMaintenance m1, FacilityMaintenance m2) {
                    return (m2.getCost() > m1.getCost()) ? -1 : 1;
                }
            });

            return facilityProblems;
        } catch (Exception se) {
            System.err.println("MaintenanceService: Threw an Exception "
                    + "listing all facility problems.");
            System.err.println(se.getMessage());
        }
        return null;
    }

    */
}
