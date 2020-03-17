package clientView;

import facilityBase.Facility;
import facilityBase.FacilityDetails;
import facilityUse.FacilityUse;
import facilityUse.Inspections;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import services.FacilityBaseService;
import services.FacilityUseService;

import java.time.LocalDate;
import java.util.List;

public class FacilityUseUI {

    private final FacilityUseService useService;
    private final FacilityBaseService facilityService;

    public FacilityUseUI(FacilityUseService useService, FacilityBaseService facilityService) {
        this.useService = useService;
        this.facilityService = facilityService;
    }

    public void runUseUI(){

        ApplicationContext context = new FileSystemXmlApplicationContext("src/resources/app-context.xml");
        System.out.println("***************** Application Context instantiated! ******************");

        // TODO: Change these objects to persistence
        //set up facilities for dummy data
        Facility fact1 = (Facility) context.getBean("Facility");
        FacilityDetails factDet1 = (FacilityDetails) context.getBean("FacilityDetails");
        fact1.setFacilityNumber(1);
        fact1.setFacilityDetails(factDet1);

        Facility fact3 = (Facility) context.getBean("Facility");
        FacilityDetails factDet3 = (FacilityDetails) context.getBean("FacilityDetails");
        fact3.setFacilityNumber(3);
        fact3.setFacilityDetails(factDet3);

        Facility fact4 = (Facility) context.getBean("Facility");
        FacilityDetails factDet4 = (FacilityDetails) context.getBean("FacilityDetails");
        fact4.setFacilityNumber(4);
        fact4.setFacilityDetails(factDet4);

        Facility fact7 = (Facility) context.getBean("Facility");
        FacilityDetails factDet7 = (FacilityDetails) context.getBean("FacilityDetails");
        fact7.setFacilityNumber(7);
        fact7.setFacilityDetails(factDet7);

        System.out.println("\nUseClient: *************** Listing the inspections at a facility *************************");

        //uses sample dummy data for inspections in database
        System.out.println("\n\tInspections At Facility #" + fact7.getFacilityNumber());
        for (Inspections inspec : useService.listInspections(fact7)) {
            System.out.println("\t" + inspec.getInspectionDetails() +
                    " date: " + inspec.getInspectionDate());
        }

        //set up new facility 12 to be checked if it's in use during an interval
        FacilityUse factUse = (FacilityUse) context.getBean("FacilityUse");
        factUse.setFacilityNumber(12);
        FacilityDetails factDet = (FacilityDetails) context.getBean("FacilityDetails");
        factDet.setFacilityName("Test Facility");
        factDet.setFacilityNumber(12);
        factUse.setFacilityDetails(factDet);
        facilityService.addFacility(factUse);
        factUse.setStartDate(LocalDate.of(2015, 12, 1));
        factUse.setEndDate(LocalDate.of(2017, 12, 1));

        System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

        boolean result = useService.isInUseDuringInterval(factUse);

        System.out.print("\tFacility #" + factUse.getFacilityNumber());
        if (result) {
            System.out.print(" IS ");
        } else {
            System.out.print(" is NOT ");
        }
        System.out.print("in use from " + factUse.getStartDate() + " to " + factUse.getEndDate() + ".\n");

        //assign the facility to use during the previously checked room and start/end date
        System.out.println("\nUseClient: ************ Assigning a facility to use ***************");
        useService.assignFacilityToUse(factUse);
        System.out.println("\nUseClient: ************ Facility and room assigned ***************");

        //re-check if facility is in use
        System.out.println("\nUseClient: ************ Checking if a facility is in use during an interval ***************");

        boolean result2 = useService.isInUseDuringInterval(factUse);

        System.out.print("\tFacility #" + factUse.getFacilityNumber());
        if (result2) {
            System.out.print(" IS ");
        } else {
            System.out.print(" is NOT ");
        }
        System.out.print("in use from " + factUse.getStartDate() + " to " + factUse.getEndDate() + ".\n");


        //list actual usage that has been assigned to a particular facility
        System.out.println("\nUseClient: ************ Listing the usage at a facility before being vacated***************");

        //uses sample dummy data of usage in database
        List<FacilityUse> usageList = useService.listActualUsage(factUse);
        Object[][] usage = new Object[usageList.size() + 1][3];
        usage[0] = new Object[] {"Room #", "Start Date", "End Date"};
        for (int i = 1; i <= usageList.size(); i++) {
            usage[i] = new Object[] {usageList.get(i-1).getStartDate().toString(),
                    usageList.get(i-1).getEndDate().toString()};
            if ((int) usage[i][0] == 0) {
                usage[i][0] = "all";
            }
        }
        System.out.println("Usage at Facility #" + factUse.getFacilityNumber());
        for (Object[] row : usage) {
            System.out.format("\t%-10s%-15s%-15s\n", row);
        }

        System.out.println("\nUseClient: ************ Vacate a facility  ***************");
        useService.vacateFacility(factUse);
        System.out.println("\nUseClient: ************ Facility vacated  ***************");

        //list actual usage that has been assigned to a particular facility
        System.out.println("\nUseClient: ************ Listing the usage at a facility after being vacated***************");

        //uses sample dummy data of usage in database
        List<FacilityUse> usageList2 = useService.listActualUsage(factUse);
        Object[][] usage2 = new Object[usageList2.size() + 1][3];
        usage2[0] = new Object[] {"Room #", "Start Date", "End Date"};
        for (int i = 1; i <= usageList2.size(); i++) {
            usage2[i] = new Object[] {usageList2.get(i-1).getStartDate().toString(),
                    usageList2.get(i-1).getEndDate().toString()};
            if ((int) usage2[i][0] == 0) {
                usage2[i][0] = "all";
            }
        }
        System.out.println("Usage at Facility #" + factUse.getFacilityNumber());
        for (Object[] row : usage2) {
            System.out.format("\t%-10s%-15s%-15s\n", row);
        }



        //calculate current usage rate of a facility
        System.out.println("\nUseClient: ************ Calculating the current usage rate at a facility ***************");
        int usageRate = (int) (useService.calcUsageRate(fact3) * 100);
        System.out.println("Current usage at Facility #" + fact3.getFacilityNumber() + " is " + usageRate + "%.");


        //remove this new facility 12 for next time UseClient is run
        facilityService.removeFacility(12);

    }
}
