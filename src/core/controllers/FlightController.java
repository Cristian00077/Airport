package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Passenger;
import core.models.Plane;
import core.models.persistance.JsonFlight;
import core.models.single.FlightDelay;
import core.models.storage.StorageFlight;
import core.models.storage.StorageLocation;
import core.models.storage.StoragePassenger;
import core.models.storage.StoragePlane;
import java.time.LocalDateTime;

public class FlightController {
    public static Response createFlight(
            String id,
            String planeID,
            String departureLocationID,
            String scaleLocationID,
            String arrivalLocationID,
            String year,
            String month,
            String day,
            String hour,
            String minutes,
            String hoursDurationArrival,
            String minutesDurationArrival,
            String hoursDurationScale,
            String minutesDurationScale) {
        try {
            if (id == null || id.isEmpty()) {
                return new Response("Id must be not empty", Status.BAD_REQUEST);
            }
            if (!isValidFlightIdFormat(id)) {
                return new Response("Id format invalid, must be 3 Upercase letters and 3 numbers", Status.BAD_REQUEST);
            }

            try {
                
            } catch (Exception e) {
                if (planeID == null || planeID.isEmpty()) {
                return new Response("Plane must be selected", Status.BAD_REQUEST);
            }
            }

            if (departureLocationID == null || departureLocationID.isEmpty()) {
                return new Response("Departure location must be selected", Status.BAD_REQUEST);
            }

            if (arrivalLocationID == null || arrivalLocationID.isEmpty()) {
                return new Response("Arrival location must be selected", Status.BAD_REQUEST);
            }

            if (scaleLocationID == null || scaleLocationID.isEmpty()) {
                return new Response("Arrival location must be selected", Status.BAD_REQUEST);
            }
            
            int yearInt;
            int monthInt;
            int dayInt;
            int hourInt;
            int minutesInt;

            try {

                yearInt = Integer.parseInt(year);
                if (yearInt < LocalDateTime.now().getYear() - 1) {
                    return new Response("Year must be greater than " + (LocalDateTime.now().getYear() - 1), Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (year.equals("")) {
                    return new Response("Year departure must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Year departure must be just numeric", Status.BAD_REQUEST);
            }

            try {
                monthInt = Integer.parseInt(month);
                if (monthInt > 12) {
                    return new Response("Month departure invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Month departure must be selected", Status.BAD_REQUEST);
            }

            try {
                dayInt = Integer.parseInt(day);
                if (dayInt > 31) {
                    return new Response("Day departure invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Day departure must be selected", Status.BAD_REQUEST);
            }

            try {
                hourInt = Integer.parseInt(hour);
                if (hourInt < 0) {
                    return new Response("Hour departure must be greater than 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Hour departure must be selected", Status.BAD_REQUEST);
            }

            try {
                minutesInt = Integer.parseInt(minutes);
                if (minutesInt < 0 || minutesInt > 59) {
                    return new Response("Minutes departure must be greater than 0", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Minutes departure must be selected", Status.BAD_REQUEST);
            }

            int hourArrival;
            try {
                hourArrival = Integer.parseInt(hoursDurationArrival);
                if (hourArrival < 0 || hourArrival > 23) {
                    return new Response("Hours of arrival duration must be a non-negative integer", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Hours of arrival duration must be selected", Status.BAD_REQUEST);
            }

            int minuteArrival;
            try {
                minuteArrival = Integer.parseInt(minutesDurationArrival);
                if (minuteArrival < 0 || minuteArrival > 59) {
                    return new Response("Minutes of arrival duration must be between 0 and 59", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Minutes of arrival duration must be selected", Status.BAD_REQUEST);
            }

            if (hoursDurationScale == null || hoursDurationScale.isEmpty()) {
                return new Response("Hours of scale duration must be not empty", Status.BAD_REQUEST);
            }
            int hourScale;
            try {
                hourScale = Integer.parseInt(hoursDurationScale);
                if (hourScale < 0) {
                    return new Response("Hours of scale duration must be a non-negative integer", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Hours of scale duration must be selected", Status.BAD_REQUEST);
            }

            if (minutesDurationScale == null || minutesDurationScale.isEmpty()) {
                return new Response("Minutes of scale duration must be not empty", Status.BAD_REQUEST);
            }
            int minuteScale;
            try {
                minuteScale = Integer.parseInt(minutesDurationScale);
                if (minuteScale < 0 || minuteScale > 59) {
                    return new Response("Minutes of scale duration must be between 0 and 59", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Minutes of scale duration must be selected", Status.BAD_REQUEST);
            }

            LocalDateTime departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minutesInt);

            StorageLocation storageLocation = StorageLocation.getInstance();

            Location departureLocation = storageLocation.getLocation(arrivalLocationID);
            Location scaleLocation = storageLocation.getLocation(scaleLocationID);
            Location arrivalLocation = storageLocation.getLocation(arrivalLocationID);

            StoragePlane storagePlane = StoragePlane.getInstance();
            Plane plane = storagePlane.getPlane(planeID);

            StorageFlight storageFlight = StorageFlight.getInstance();
            Flight flight = storageFlight.getFlight(id);

            if (flight == null) {
                return new Response("Flight not found", Status.NOT_FOUND);
            }

            if (scaleLocation != null) {
                if (!storageFlight.addFlight(new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hourArrival, minuteArrival))) {
                    return new Response("This flight already exits", Status.BAD_REQUEST);
                }
                return new Response("Flight created successfully", Status.CREATED);
            } else {
                if (!storageFlight.addFlight(new Flight(id, plane, departureLocation, scaleLocation, arrivalLocation, departureDate, hourArrival, minuteArrival, hourScale, minuteArrival))) {
                    return new Response("This flight already exits", Status.BAD_REQUEST);
                }
                return new Response("Flight with scale created successfully", Status.CREATED);
            }
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response addPassengerToFlight(String PassId, String flightId){
        try {
            long passIdLong;
            try {
                passIdLong = Long.parseLong(PassId);
            } catch (NumberFormatException e) {
                return new Response("Passenger id must be numeric", Status.BAD_REQUEST);
            }
            
            if(flightId.isEmpty()){
                return new Response("There are not flights to add passengers", Status.BAD_REQUEST);
            }
            
            StorageFlight storageflight = StorageFlight.getInstance();
            StoragePassenger storagePass = StoragePassenger.getInstance();  
            
            Flight flight = storageflight.getFlight(flightId);
            if(flight == null){
                return new Response("Flight not found", Status.NOT_FOUND);
            }
            Passenger passenger = storagePass.getPassenger(passIdLong);
            if(passenger == null){
                return new Response("Passenger not found", Status.NOT_FOUND);
            }
            
            if(flight.getNumPassengers() >= flight.getPlane().getMaxCapacity()){
                return new Response("Plane already have max capacity", Status.BAD_REQUEST);
            }
            if(flight.getPassengers().contains(passenger)){
                return new Response("Paasenger already exits in these flight", Status.BAD_REQUEST);
            }
            flight.addPassenger(passenger);
            passenger.addFlight(flight);
            return new Response("Passenger added successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    private static boolean isValidFlightIdFormat(String id) {
        return id.matches("^[A-Z]{3}\\d{3}$");
    }

    public static Response delayFlight(String flightId, String hours, String minutes) {
        try {
            int hoursInt, minutesInt;
            if (flightId.isEmpty()) {
            return new Response("Flight Id is required", Status.BAD_REQUEST);
            }
            try {
                hoursInt = Integer.parseInt(hours);
                minutesInt = Integer.parseInt(minutes);
                
                if (hoursInt < 0 || minutesInt < 0) {
                    return new Response("Delay time must be positive", Status.BAD_REQUEST);
                }
                if (hoursInt == 0 && minutesInt == 0) {
                    return new Response("Delay time must be greater than 00:00", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Hours and minutes must be numeric", Status.BAD_REQUEST);
            }
            
            StorageFlight storageFlight = StorageFlight.getInstance();
            Flight flight = storageFlight.getFlight(flightId);
            
            if (flight == null) {
            return new Response("Flight not found", Status.NOT_FOUND);
            }
            FlightDelay.delay(flight, hoursInt, minutesInt);
            return new Response("Flight delayed successfully", Status.OK);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.BAD_REQUEST);
        }
       
    }

    public static LocalDateTime dateTimeObject(String year, String month, String day, String hour, String minutes) {
        try {

        } catch (NumberFormatException e) {

        }
        return null;
    }

}
