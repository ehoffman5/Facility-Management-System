package facilityMaintenance;

import java.util.*;

public class DelayedRequests extends Requests {
    // Variable declarations
    Date projectedStartDate;
    int timeSinceDelayed;

    // Constructor to initialize class attributes
    public DelayedRequests() {
        projectedStartDate = new Date();
        timeSinceDelayed = 0;
    }

    // Function for setting delayed requests back to active
    public void reinitiateRequest() {

    }

    // Function for listing all requests that are currently on hold
    public void listPendingRequests() {

    }
}