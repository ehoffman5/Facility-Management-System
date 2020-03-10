package sqlData;

import database.DBConnector;
import facilityBase.Facility;
import facilityUse.FacilityUse;
import facilityUse.Inspections;
import interfaces.FacilityUseDataImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FacilityUseData implements FacilityUseDataImpl {

    // Data class separates DB management concerns from service classes

    // ---------- FacilityUse Methods ---------- //
    // Check to see if a facility is in use
    public boolean isInUseDuringInterval(FacilityUse facUse) {

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


    // ---------- Inspections Methods ---------- //
    // Function for showing all assigned inspections
    public List<Inspections> listInspections(Facility fac) {

        List<Inspections> listOfInspec = new ArrayList<Inspections>();

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String listInspectionsQuery = "SELECT * FROM Inspections WHERE "
                    + "facility_number = '" + fac.getFacilityNumber() + "'";

            ResultSet useRS = st.executeQuery(listInspectionsQuery);
            System.out.println("Use: *************** Query " + listInspectionsQuery + "\n");

            while ( useRS.next() ) {
                Inspections inspec = new Inspections();
                inspec.setInspectionDate(useRS.getDate("inspection_date").toLocalDate());
                inspec.setInspectionDetails(useRS.getString("inspection_detail"));
                inspec.setFacilityNumber(fac.getFacilityNumber());
                listOfInspec.add(inspec);
            }

            //close to manage resources
            useRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("Use: Threw a SQLException retrieving "
                    + "inspections from Inspections table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfInspec;
    }


    // ---------- Inspections Methods ---------- //
    // Display how much a facility is used
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

    // Empty all occupants from facility
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
