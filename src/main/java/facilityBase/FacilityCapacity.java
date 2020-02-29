package facilityBase;

import java.util.HashMap;

public class FacilityCapacity {

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
    // Add new facility
    public void requestAvailableCapacity(int facilityCapacity) {
        System.out.println(facilityCapacity);
    }
}
