package facilityMaintenance;

import facilityBase.Facility;
import facilityUse.FacilityUse;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.sql.*;
import java.time.*;
import facilityMaintenance.DownTime;

public class Problems extends FacilityMaintenance {

    // Function for calculating the ratio of problems a specific facility causes
    public double calcProblemRateForFacility(Facility fac) {
        FacilityUse useService = new FacilityUse();
        DownTime downTimeService = new DownTime();
        try {
            LocalDate facilityStartDate = useService.getStartDate();
            double totalDays = ChronoUnit.DAYS.between(facilityStartDate, LocalDate.now());
            return downTimeService.calcDownTimeForFacility(fac) / totalDays;
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception calculating "
                    + "the down time for a facility.");
            System.err.println(se.getMessage());
        }

        return 0;
    }

    // Function to list all problem requests
    public List<FacilityMaintenance> listFacilityProblems(Facility fac) {
        List<FacilityMaintenance> facilityProblems = new ArrayList<FacilityMaintenance>();
        FacilityMaintenance maintObj = new FacilityMaintenance();

        try {
            facilityProblems.addAll(maintObj.listFacilityMaintRequests(fac));
            facilityProblems.addAll(maintObj.listMaintenance(fac));

            //sort problems by cost
            Collections.sort(facilityProblems, new Comparator<FacilityMaintenance>() {
                @Override
                public int compare(FacilityMaintenance m1, FacilityMaintenance m2) {
                    return (m2.getCost() > m1.getCost()) ? -1 : 1;
                }
            });

            return facilityProblems;
        } catch (Exception se) {
            System.err.println("Maintenance: Threw an Exception "
                    + "listing all facility problems.");
            System.err.println(se.getMessage());
        }
        return null;
    }


}
