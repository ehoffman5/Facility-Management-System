package facilityBase;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FacilityDetailsTest extends Facility{

    @Test
    public void testFacilityName() {
        FacilityDetails facilityDetails = new FacilityDetails();
        facilityDetails.setFacilityName("Name");

        String facilityName = facilityDetails.getFacilityName();

        assertEquals(facilityName, "Name");
    }

    @Test
    public void testFacilityPhoneNo() {
        FacilityDetails facilityDetails = new FacilityDetails();
        facilityDetails.setFacilityPhoneNo("Phone");

        String facilityPhoneNo = facilityDetails.getFacilityPhoneNo();

        assertEquals(facilityPhoneNo, "Phone");
    }
}