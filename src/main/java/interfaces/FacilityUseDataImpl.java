package interfaces;

import facilityBase.Facility;
import facilityUse.FacilityUse;
import facilityUse.Inspections;

import java.util.List;

public interface FacilityUseDataImpl {

    public boolean isInUseDuringInterval(FacilityUse facUse);
    public List<Inspections> listInspections(Facility fac);
    public List<FacilityUse> listActualUsage(Facility fac);
    public double calcUsageRate(Facility fac);
    public void vacateFacility(Facility fac);

}
