package clientView;

import facilityBase.Facility;
import facilityBase.FacilityDetails;

import java.util.HashMap;

public class facilityBaseUI {
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