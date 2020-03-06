package facilityUse;

import database.DBConnector;
import facilityBase.Facility;
import java.util.*;
import java.sql.*;
import java.time.*;

public class Usage extends FacilityUse {

     public List<FacilityUse> listActualUsage(Facility fac) {

        List<FacilityUse> listOfUsage = new ArrayList<FacilityUse>();

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String listUsageQuery = "SELECT * FROM FacilityUse WHERE facility_number = '" +
                    fac.getFacilityNumber() + "' ORDER BY start_date";

            ResultSet useRS = st.executeQuery(listUsageQuery);
            System.out.println("Use: *************** Query " + listUsageQuery + "\n");

            while ( useRS.next() ) {
                FacilityUse use = new FacilityUse();
                use.setFacilityNumber(fac.getFacilityNumber());
                use.setPurposeOfUse(useRS.getString("purpose_of_use"));
                use.setStartDate(useRS.getDate("start_date").toLocalDate());
                use.setEndDate(useRS.getDate("end_date").toLocalDate());
                listOfUsage.add(use);
            }

            //close to manage resources
            useRS.close();
            st.close();
            return listOfUsage;

        }
        catch (SQLException se) {
            System.err.println("Use: Threw a SQLException retrieving list of usage from FacilityUse table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }

    // calculate total rate of facilities currently in use
    public double calcUsageRate(Facility fac) {

        try {  // equation: total facilities / facilities in use

            Statement st = DBConnector.getConnection().createStatement();
            String getUsageRate = "SELECT count(reservation_number)/count(facility_number) FROM facilityUse, Facility";

            ResultSet useRS = st.executeQuery(getUsageRate);
            System.out.println("Use: *************** Query " + getUsageRate + "\n");
            return 1;

        } catch (Exception se) {
            System.err.println("Use: Threw an Exception retrieving list of usage for calculating the usage rate.");
            System.err.println(se.getMessage());
        }

        return 0.00;

    }

    public void vacateFacility(Facility fac) {

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String vacateQuery = "";

            List<FacilityUse> usageList = listActualUsage(fac);
            for (FacilityUse use : usageList) {
                //if room number matches usage list and room is currently in use, set vacateQuery
                if ( (LocalDate.now().equals(use.getStartDate()) || LocalDate.now().isAfter(use.getStartDate()))
                        & LocalDate.now().equals(use.getEndDate())
                        || LocalDate.now().isBefore(use.getEndDate()) ) {
                    vacateQuery = "UPDATE FacilityUse SET end_date = '" + java.sql.Date.valueOf(LocalDate.now().minusDays(1)) +
                            "' WHERE facility_number = " + fac.getFacilityNumber() +
                            "AND start_date = '" + java.sql.Date.valueOf(use.getStartDate()) + "'";
                }
            }

            st.execute(vacateQuery);
            System.out.println("Use: *************** Query " + vacateQuery + "\n");

        }
        catch (SQLException se){
            System.err.println("Use: Threw a SQLException vacating the facility.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }
}
