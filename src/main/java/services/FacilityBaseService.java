package services;

import facilityBase.Facility;
import facilityBase.FacilityCapacity;
import interfaces.FacilityBaseServiceInterface;
import sqlData.FacilityBaseData;

import java.util.List;

public class FacilityBaseService implements FacilityBaseServiceInterface {

    // Service class separates concerns from main classes with getter and setter methods

    private FacilityBaseData facData = new FacilityBaseData();

    // ---------- Facility Base Methods ---------- //
    // Add new facility
    public void addFacility(Facility facility) {
        try {
            facData.addFacility(facility);
        } catch (Exception se) {
            System.err.println("Facility: Threw an Exception adding new facility.");
            System.err.println(se.getMessage());
        }
    }

    // Remove a facility
    public void removeFacility(int facilityNumber) {
        try {
            facData.removeFacility(facilityNumber);
        } catch (Exception se) {
            System.err.println("Facility: Threw an Exception removing facility.");
            System.err.println(se.getMessage());
        }
    }

    // Print out a list of all current facilities
    public List<Facility> listFacilities() {
        try {
            return facData.listFacilities();
        } catch (Exception se) {
            System.err.println("Facility: Threw an Exception retrieving list of facilities.");
            System.err.println(se.getMessage());
        }

        return null;
    }


    // ---------- Facility Details Methods ---------- //
    // Function for reading details
    public void addFacilityDetails(int facilityNumber, String facilityPhoneNo, String facilityName) {
        try {
            facData.addFacilityDetails(facilityNumber, facilityPhoneNo, facilityName);
        } catch (Exception se) {
            System.err.println("Facility: Threw an Exception updating details in facilityDetails.");
            System.err.println(se.getMessage());
        }
    }

    // Function for listing facility details
    public Facility getFacilityInformation(int facilityNumber) {
        try {
            Facility fac = facData.getFacilityInformation(facilityNumber);
            return fac;
        } catch (Exception se) {
            System.err.println("Facility: Threw an Exception retrieving facility information.");
            System.err.println(se.getMessage());
        }
        return null;
    }


    // ---------- Facility Capacity Methods ---------- //
    // Print the capacity of the given facility
    public int requestAvailableCapacity(FacilityCapacity fac) {
        return fac.getFacilityCapacity().getCapacity();
    }
}