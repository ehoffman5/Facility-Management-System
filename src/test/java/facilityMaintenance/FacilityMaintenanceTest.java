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

        assertEquals(maintRequestDescription, facilityMaintenance.getMaintRequestDescription());
    }

    @Test
    public void testCost() {

        FacilityMaintenance facilityMaintenance = new FacilityMaintenance();
        facilityMaintenance.setCost(134);

        float cost = facilityMaintenance.getCost();

        assertEquals(cost, 134, facilityMaintenance.getCost());
    }
}