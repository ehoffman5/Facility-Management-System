package facilityBase;

import java.util.HashMap;

public class FacilityCapacity extends Facility {

    // Variable declarations
    private int facilityCapacity;

    // Constructor to initialize class attributes
    public FacilityCapacity() { }


    // ---------- Getter and Setter Methods ---------- //
    public void setFacilityCapacity(int facilityCapacity) {
        this.facilityCapacity = facilityCapacity;
    }

    public int getFacilityCapacity() {
        return facilityCapacity;
    }


    // ---------- CRUD Methods ---------- //
    // Print the capacity of the given facility
    public int requestAvailableCapacity(Facility fac) {
        return facilityCapacity;  // TODO: turn into a SELECT * statement
    }
}
