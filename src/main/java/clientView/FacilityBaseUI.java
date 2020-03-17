package clientView;

import facilityBase.Facility;
import facilityBase.FacilityCapacity;
import facilityBase.FacilityDetails;
import services.FacilityBaseService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

public class FacilityBaseUI {

    private final FacilityBaseService facService;

    public FacilityBaseUI(FacilityBaseService facService) {
        this.facService = facService;
    }

    public void runBaseUI(){

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
        FacilityCapacity factCap4 = (FacilityCapacity) context.getBean("FacilityCapacity");
        fact4.setFacilityNumber(4);
        fact4.setFacilityDetails(factDet4);
        fact4.setFacilityCapacity(factCap4);

        Facility fact7 = (Facility) context.getBean("Facility");
        FacilityDetails factDet7 = (FacilityDetails) context.getBean("FacilityDetails");
        fact7.setFacilityNumber(7);
        fact7.setFacilityDetails(factDet7);


        System.out.println("\nFacilityClient: *************** Instantiating a facility and its details *************************");
        Facility fact = (Facility) context.getBean("Facility");
        fact.setFacilityNumber(11);
        FacilityDetails detail = (FacilityDetails) context.getBean("FacilityDetails");
        detail.setFacilityNumber(11);
        detail.setFacilityName("IT Center");
        detail.setFacilityPhoneNo("5550123");
        fact.setFacilityDetails(detail);

        //save facility information
        //Saving the newly created facility and its details
        facService.addFacility(fact);
        System.out.println("FacilityClient: *************** Facility is inserted in Facility Database *************************");

        System.out.println("FacilityClient: *************** Trying to get information about this facility in the database ***************");

        Facility searchedFacility = facService.getFacilityInformation(11);

        System.out.println("\nFacilityClient: *************** Here is searched facility information *************************");
        System.out.println("\n\tFacility Number:   \t\t" + searchedFacility.getFacilityNumber());
        FacilityDetails facilityDet = searchedFacility.getFacilityDetails();
        System.out.println("\tInfo About Facility:  \t" + facilityDet.getFacilityName());
        if (facilityDet.getFacilityPhoneNo() != null) {
            System.out.print("\t\t\t\t Phone Number: " + facilityDet.getFacilityPhoneNo() +
                    "\n\t\t\t\t" + "\n");
        } else {
            System.out.print("\t\t\t\t Phone Number: unlisted" +
                    "\n\t\t\t\t" + "\n");
        }


        //add optional phone number to facility detail
        facService.addFacilityDetails(11, "3120136", "Damen");

        Facility updatedFacility = facService.getFacilityInformation(11);
        FacilityDetails facilityNewDet = updatedFacility.getFacilityDetails();

        System.out.println("\nFacilityClient: *************** Here is the updated facility information *************************");
        System.out.println("\n\tFacility Number:   \t\t" + updatedFacility.getFacilityNumber());
        System.out.println("\tInfo About Facility:  \t" + facilityNewDet.getFacilityName());
        if (facilityNewDet.getFacilityPhoneNo() != null) {
            System.out.print("\t\t\t\t Phone Number: " + facilityNewDet.getFacilityPhoneNo() +
                    "\n\t\t\t\t" + "\n");
        } else {
            System.out.print("\t\t\t\t Phone Number: unlisted" +
                    "\n\t\t\t\t" + "\n");
        }


        System.out.println("\nFacilityClient: *************** Remove a facility from the database *************************");
        facService.removeFacility(11);
        System.out.println("************ Facility Removed ************");

        System.out.println("\nFacilityClient: *************** An updated list of all the facilities *************************");
        List<Facility> listOfFacilities = facService.listFacilities();
        for (Facility fac : listOfFacilities) {
            FacilityDetails facDet = fac.getFacilityDetails();
            System.out.println("\n\t" + facDet.getFacilityName() + " Number: " + fac.getFacilityNumber());
        }


        System.out.println("\nFacilityClient: *************** Request available capacity of a facility *************************");
        //uses sample data
        int capacityAvail = facService.requestAvailableCapacity(fact4.getFacilityCapacity());
        System.out.println("There are " + capacityAvail + " occupancy spaces currently available at Facility #" + fact4.getFacilityNumber() + ".");

    }
}