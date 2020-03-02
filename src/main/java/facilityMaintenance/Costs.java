package facilityMaintenance;

import database.DBConnector;
import facilityBase.Facility;
import java.util.*;
import java.sql.*;

public class Costs extends FacilityMaintenance {

    // Function for calculating the cost of a maintenance request per facilityBase
    public int calcMaintCostForFacility(Facility fac) {

        try {

            int totalCost = 0;

            Statement st = DBConnector.getConnection().createStatement();
            String calcMaintenanceCostQuery = "SELECT SUM(cost) FROM maintenance "
                    + "WHERE facility_id = " + fac.getFacilityNumber();
            ResultSet maintRS = st.executeQuery(calcMaintenanceCostQuery);

            while ( maintRS.next() ) {
                totalCost = maintRS.getInt(1);
            }
            System.out.println("MaintenanceDAO: *************** Query " + calcMaintenanceCostQuery + "\n");

            //close to manage resources
            maintRS.close();
            st.close();

            return totalCost;

        }
        catch (SQLException se) {
            System.err.println("MaintenanceDAO: Threw a SQLException calculating total "
                    + "maintenance cost from maintenance table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return 0;
    }
}
