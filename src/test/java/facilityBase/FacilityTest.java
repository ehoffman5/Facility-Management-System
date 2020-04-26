package facilityBase;

import org.junit.Test;
import static org.junit.Assert.*;

public class FacilityTest {

    @Test
    public void testFacilityNumber() {

        Facility facility = new Facility();
        facility.setFacilityNumber(123);

        int facilityNumber = facility.getFacilityNumber();

        assertEquals(123, facilityNumber);
    }

}