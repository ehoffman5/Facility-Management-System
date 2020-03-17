package facilityBase;

public class FacilityCapacity extends Facility {

    // Variable declarations
    private int capacity;

    // Constructor to initialize class attributes
    public FacilityCapacity() { }


    // ---------- Getter and Setter Methods ---------- //
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }
}