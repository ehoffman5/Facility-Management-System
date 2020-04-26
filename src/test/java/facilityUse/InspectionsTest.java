package facilityUse;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class InspectionsTest  {

    @Test
    public void testInspectionDate() {
        Inspections inspections = new Inspections();
        inspections.setInspectionDate(LocalDate.parse("2020-01-01"));

        LocalDate inspectionDate = inspections.getInspectionDate();

        assertEquals(inspectionDate, LocalDate.parse("2020-01-01"));

    }

    @Test
    public void testInspectionDetails() {
        Inspections inspections = new Inspections();
        inspections.setInspectionDetails("Details");

        String inspectionDetails = inspections.getInspectionDetails();

        assertEquals(inspectionDetails, "Details");
    }
}