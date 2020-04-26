package facilityUse;

import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.*;

public class FacilityUseTest {

    @Test
    public void testPurposeOfUse() {

        FacilityUse facilityUse = new FacilityUse();
        facilityUse.setPurposeOfUse("Test");

        String purposeOfUse = facilityUse.getPurposeOfUse();

        assertEquals("Test", purposeOfUse);
    }

    @Test
    public void testStartDate() {

        FacilityUse facilityUse = new FacilityUse();
        facilityUse.setStartDate(LocalDate.parse("2018-11-01"));

        LocalDate startDate = facilityUse.getStartDate();

        assertEquals(LocalDate.parse("2018-11-01"), startDate);
    }

    @Test
    public void testEndDate() {

        FacilityUse facilityUse = new FacilityUse();
        facilityUse.setEndDate(LocalDate.parse("2018-11-01"));

        LocalDate endDate = facilityUse.getEndDate();

        assertEquals(LocalDate.parse("2018-11-01"), endDate);
    }
}