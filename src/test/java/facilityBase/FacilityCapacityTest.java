package facilityBase;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class FacilityCapacityTest {

    @Test
    public void testCapacity() {

        FacilityCapacity facilityCapacity = new FacilityCapacity();
        facilityCapacity.setCapacity(134);

        int capacity = facilityCapacity.getCapacity();

        assertEquals(capacity, 134);
    }
}