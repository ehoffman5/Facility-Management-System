package facilityMaintenance;

import java.util.*;


public class FacilityMaintenance {
    // Variable declarations
    private float cost;
    private String maintRequestDescription;

    // Constructor to initialize class attributes
    public FacilityMaintenance() {
    }


    // ---------- Getter and Setter Methods ---------- //
    public void setMaintRequestDescription(String maintRequestDescription) {
        this.maintRequestDescription = maintRequestDescription;
    }

    public String getMaintRequestDescription() {
        return maintRequestDescription;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }


    // Method for submitting a new request
    public void makeFacilityMaintRequest() {

    }

    // Print out a list of all current requests
    public void listFacilityMaintRequests() {

    }

    // Method for setting request to an active order
    public void scheduleMaintenance() { }

    // Prints out a list of all active maintenance orders
    public void listMaintenance() { }
}