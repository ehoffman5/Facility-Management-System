package facilityBase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FacilityDetailsTest extends Facility{

    @Test
    public void testFacilityName() {
        FacilityDetails facilityDetails = new FacilityDetails();
        facilityDetails.setFacilityName("Name");

        String facilityName = facilityDetails.getFacilityName();

        assertEquals("Name", facilityName);
    }

    @Test
    public void testFacilityPhoneNo() {
        FacilityDetails facilityDetails = new FacilityDetails();
        facilityDetails.setFacilityPhoneNo("800-888-8888");

        String facilityPhoneNo = facilityDetails.getFacilityPhoneNo();

        assertEquals("800-888-8888", facilityPhoneNo);
    }
}