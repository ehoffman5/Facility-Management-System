package clientView;

import facilityBase.Facility;
import facilityBase.FacilityDetails;

import java.util.ArrayList;
import java.util.HashMap;

public class facilityBaseUI {
    public static void main(String[] args) {

        ArrayList<String> myDetailsX = new ArrayList<String>();
        ArrayList<String> myDetailsY = new ArrayList<String>();
        FacilityDetails x = new FacilityDetails(myDetailsX);
        FacilityDetails y = new FacilityDetails(myDetailsY);

        x.addFacilityDetails("Damen", "800-888-9999");
        y.addFacilityDetails("Cuneo", "800-777-8888");
        x.getFacilityInformation();
        y.getFacilityInformation();

        HashMap<Integer, FacilityDetails> myFacilities =  new HashMap<Integer, FacilityDetails>();
        Facility obj = new Facility(myFacilities);

        obj.addFacility(100, x);
        obj.addFacility(200, y);
        obj.removeFacility(100);
        obj.listFacilities();
    }
}