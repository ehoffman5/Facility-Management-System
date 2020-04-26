package facilityMaintenance;

import facilityBase.Facility;
import org.junit.Test;
import static org.junit.Assert.*;

public class FacilityMaintenanceTest extends Facility {

    @Test
    public void testMaintenanceDescription() {

        FacilityMaintenance facilityMaintenance = new FacilityMaintenance();
        facilityMaintenance.setMaintRequestDescription("Test");

        String maintRequestDescription = facilityMaintenance.getMaintRequestDescription();

        assertEquals("Test", maintRequestDescription);
    }

    @Test
    public void testCost() {

        FacilityMaintenance facilityMaintenance = new FacilityMaintenance();
        facilityMaintenance.setCost(134);

        float cost = facilityMaintenance.getCost();

        assertEquals(134, cost, 0);
    }
}