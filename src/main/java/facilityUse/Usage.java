package facilityUse;

import database.DBConnector;
import facilityBase.Facility;
import java.util.*;
import java.sql.*;

public class Usage extends FacilityUse {

     public List<FacilityUse> listActualUsage(Facility fac) {

        List<FacilityUse> listOfUsage = new ArrayList<FacilityUse>();

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String listUsageQuery = "SELECT * FROM use WHERE facility_id = '" +
                    fac.getFacilityNumber() + "' ORDER BY room_number, start_date";

            ResultSet useRS = st.executeQuery(listUsageQuery);
            System.out.println("Use: *************** Query " + listUsageQuery + "\n");

            while ( useRS.next() ) {
                FacilityUse use = new FacilityUse();
                use.setFacilityNumber(fac.getFacilityNumber());
                //use.setRoomNumber(useRS.getInt("room_number"));
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
            System.err.println("Use: Threw a SQLException retrieving list of usage from use table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return null;
    }

    public double calcUsageRate(Facility fac) {

        try {
            Facility facService = new Facility();
            //int totalRooms = fac.getFacilityDetails().getNumberOfRooms();
            int roomsAvailable = facService.requestAvailableCapacity(fac);
            int roomsInUse = totalRooms - roomsAvailable;
            return Math.round(((double)roomsInUse / totalRooms) * 100d)/100d;

        } catch (Exception se) {
            System.err.println("UseService: Threw an Exception retrieving list of usage for calculating the usage rate.");
            System.err.println(se.getMessage());
        }

        return 0.00;public void calcUsageRate() {

    }

    public void vacateFacility(Facility fac, int roomNumber) {

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String vacateQuery = "";

            List<FacilityUse> usageList = listActualUsage(fac);
            for (FacilityUse use : usageList) {
                //TODO these checks are also done in UseService. can they be removed from DAO method?
                //if room number matches usage list and room is currently in use, set vacateQuery
                if ((use.getRoomNumber() == roomNumber || use.getRoomNumber() == 0) & ((LocalDate.now().equals(use.getStartDate()) ||
                        LocalDate.now().isAfter(use.getStartDate())) & LocalDate.now().equals(use.getEndDate()) ||
                        LocalDate.now().isBefore(use.getEndDate()))) {
                    vacateQuery = "UPDATE use SET end_date = '" + Date.valueOf(LocalDate.now().minusDays(1)) +
                            "' WHERE facility_id = " + fac.getFacilityID() + "AND room_number = " + roomNumber +
                            "AND start_date = '" + Date.valueOf(use.getStartDate()) + "'";
                }
            }

            st.execute(vacateQuery);
            System.out.println("UseDAO: *************** Query " + vacateQuery + "\n");

        }
        catch (SQLException se){
            System.err.println("UseDAO: Threw a SQLException vacating the facility.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

    }
}
