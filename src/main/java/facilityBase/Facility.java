package facilityBase;

import java.util.*;

// remove this comment
public class Facility {
    // Variable declarations
    private int facilityNumber;
    private FacilityDetails facilityDetail;
    public static HashMap<Integer, FacilityDetails> facilities;

    // Constructor to initialize class attributes
    public Facility(HashMap<Integer, FacilityDetails> facilities) {
        this.facilities = new HashMap<Integer, FacilityDetails>();
    }

    // ---------- Getter and Setter Methods ----------
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

    // ---------- CRUD Methods ----------
    // Add new facilityBase
    public void addFacility(int facilityNumber, FacilityDetails facilityDetail) {
        facilities.put(facilityNumber, facilityDetail);
    }

    // Remove a facilityBase
    public void removeFacility(int facilityNumber) {
        facilities.remove(facilityNumber);
    }

    // Print out a list of all current facilities
    public void listFacilities() {
        System.out.println(facilities);
    }

    // Client interface method
    public static void main(String[] args) {
        FacilityDetails x = new FacilityDetails();
        FacilityDetails y = new FacilityDetails();
        HashMap<Integer, FacilityDetails> myFacilities =  new HashMap<Integer, FacilityDetails>();
        Facility obj = new Facility(myFacilities);

        obj.addFacility(100, x);
        obj.addFacility(200, y);
        obj.removeFacility(100);
        obj.listFacilities();
    }
}