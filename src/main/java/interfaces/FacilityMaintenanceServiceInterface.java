package interfaces;

import facilityBase.Facility;
import facilityMaintenance.FacilityMaintenance;
import java.util.List;

public interface FacilityMaintenanceServiceInterface {

    public FacilityMaintenance makeFacilityMaintRequest(Facility fac, String maintenanceDescription, int cost);
    public List<FacilityMaintenance> listFacilityMaintRequests(Facility fac);
    public void scheduleMaintenance(FacilityMaintenance maintRequest);
    public List<FacilityMaintenance> listMaintenance(Facility fac);
    public int calcMaintCostForFacility(Facility fac);
    public int calcDownTimeForFacility(Facility fac);
    public double calcProblemRateForFacility(Facility fac);
    public List<FacilityMaintenance> listFacilityProblems(Facility fac);

}