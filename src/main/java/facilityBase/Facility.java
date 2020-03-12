package facilityBase;

public class Facility {

    // Variable declarations
    private int facilityNumber;
    private FacilityDetails facilityDetail;
    private FacilityCapacity facilityCapacity;

    // Constructor to initialize class attributes
    public Facility() { }


    // ---------- Getter and Setter Methods ---------- //
    public void setFacilityNumber(int facilityNumber) {
        this.facilityNumber = facilityNumber;
    }

    public int getFacilityNumber() {
        return facilityNumber;
    }

    public void setFacilityDetails(FacilityDetails facilityDetail) {
        this.facilityDetail = facilityDetail;
    }

    public FacilityDetails getFacilityDetails() {
        return facilityDetail;
    }

    public void setFacilityCapacity(FacilityCapacity facilityCapacity) {
        this.facilityCapacity = facilityCapacity;
    }

    public FacilityCapacity getFacilityCapacity() {
        return facilityCapacity;
    }

}