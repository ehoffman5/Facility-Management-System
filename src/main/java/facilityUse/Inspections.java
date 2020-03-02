package facilityUse;

import database.DBConnector;
import facilityBase.Facility;
import java.util.*;
import java.sql.*;
import java.time.*;

public class Inspections extends FacilityUse {
    // Variable declarations
    LocalDate inspectionDate;
    String inspectionDetails;

    // Constructor to initialize class attributes
    public Inspections() {
    }


    // ---------- Getter and Setter Methods ---------- //
    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDetails(String inspectionDetails) {
        this.inspectionDetails = inspectionDetails;
    }

    public String getInspectionDetails() {
        return inspectionDetails;
    }


    // Function for showing all assigned inspections
    public List<Inspections> listInspections(Facility fac) {

        List<Inspections> listOfInspec = new ArrayList<Inspections>();

        try {

            Statement st = DBConnector.getConnection().createStatement();
            String listInspectionsQuery = "SELECT * FROM inspection WHERE "
                    + "facility_id = '" + fac.getFacilityNumber() + "'";

            ResultSet useRS = st.executeQuery(listInspectionsQuery);
            System.out.println("UseDAO: *************** Query " + listInspectionsQuery + "\n");

            while ( useRS.next() ) {
                Inspections inspec = new Inspections();
                // inspec.setInspectionDate(useRS.getDate("inspection_date"));  TODO: convert SQL date to LocalDate
                inspec.setInspectionDetails(useRS.getString("inspection_detail"));
                inspec.setFacilityNumber(fac.getFacilityNumber());
                listOfInspec.add(inspec);
            }

            //close to manage resources
            useRS.close();
            st.close();

        }
        catch (SQLException se) {
            System.err.println("UseDAO: Threw a SQLException retreiving "
                    + "inspections from Inspections table.");
            System.err.println(se.getMessage());
            se.printStackTrace();
        }

        return listOfInspec;
    }
}
