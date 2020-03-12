package facilityBase;

public class FacilityCapacity extends Facility {

    // Variable declarations
    private int facilityCapacity;

    // Constructor to initialize class attributes
    public FacilityCapacity() { }


    // ---------- Getter and Setter Methods ---------- //
    public void setCapacity(int facilityCapacity) {
        this.facilityCapacity = facilityCapacity;
    }

    public int getCapacity() {
        return facilityCapacity;
    }
}