package interfaces;

import facilityBase.Facility;
import facilityMaintenance.FacilityMaintenance;

import java.util.List;

public interface FacilityMaintenanceDataInterface {

    public FacilityMaintenance makeFacilityMaintRequest(Facility fac, String maintenanceDescription, int cost);
    public List<FacilityMaintenance> listFacilityMaintRequests(Facility fac);
    public void scheduleMaintenance(FacilityMaintenance maintRequest);
    public List<FacilityMaintenance> listMaintenance(Facility fac);
    public int calcMaintCostForFacility(Facility fac);

}
