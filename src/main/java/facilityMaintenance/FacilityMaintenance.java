package facilityMaintenance;

import database.DBConnector;
import facilityBase.Facility;
import java.util.*;
import java.sql.*;


public class FacilityMaintenance extends Facility {
    // Variable declarations
    private float cost;
    private String maintRequestDescription;

    // Constructor to initialize class attributes
    public FacilityMaintenance() { }


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

}