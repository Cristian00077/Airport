package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Flight;
import core.models.Location;
import core.models.Plane;
import core.models.storage.StorageFlight;
import core.models.storage.StoragePlane;
import java.time.LocalDateTime;

public class FlightController {

    public static Response createFlight(
        String id,
        String plane,
        String departureLocation,
        String scaleLocation,
        String arrivalLocation,
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
            return new Response("Id format invalid, must be 3 Upercase letters and 3 numbers",Status.BAD_REQUEST);
        }

        
        if (plane == null || plane.isEmpty()) {
            return new Response("Plane must be not empty", Status.BAD_REQUEST);
        }

        if (departureLocation == null || departureLocation.isEmpty()) {
            return new Response("Departure location must be not empty", Status.BAD_REQUEST);
        }

        if (scaleLocation == null || scaleLocation.isEmpty()) {
            return new Response("Scale location must be not empty", Status.BAD_REQUEST);
        }

        if (arrivalLocation == null || arrivalLocation.isEmpty()) {
            return new Response("Arrival location must be not empty", Status.BAD_REQUEST);
        }

        int yearInt;
        int monthInt;
        int dayInt;
        int hourInt;
        int minutesInt;
        
        try {
            yearInt = Integer.parseInt(year);
            if(yearInt < 2024){
                return new Response("Year must be greater than 2024", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            if (year.equals("")) {
               return new Response("Year must be not empty", Status.BAD_REQUEST);
            }
            return new Response("Year must be jsut numeric", Status.BAD_REQUEST);
        }
        
        try {
            monthInt = Integer.parseInt(month);
            if(monthInt < 4 ){
                return new Response("Month invalid", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Month must be selected", Status.BAD_REQUEST);
        }
        
        try {
            dayInt = Integer.parseInt(day);
            if(dayInt < 4 ){
                return new Response("Day invalid", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Day must be selected", Status.BAD_REQUEST);
        }
        
        try {
            hourInt = Integer.parseInt(hour);
            if(hourInt < 0 ){
                return new Response("Hour must be greater than 0", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Hour must be selected", Status.BAD_REQUEST);
        }
        
        try {
            minutesInt = Integer.parseInt(minutes);
            if(minutesInt < 0 || minutesInt > 59){
                return new Response("Minutes must be greater than 0", Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Minutes must be selected", Status.BAD_REQUEST);
        }
        /*if (departureDate == null || departureDate.isEmpty()) {
            return new Response("Departure date must be not empty", Status.BAD_REQUEST);
        } else {
            try {
                java.time.LocalDate.parse(departureDate);
            } catch (java.time.format.DateTimeParseException e) {
                return new Response("Departure date must be in format YYYY-MM-DD",Status.BAD_REQUEST);
            }
        }*/
        
        int hourArrival;
        try {
            hourArrival = Integer.parseInt(hoursDurationArrival);
            if (hourArrival < 0 || hourArrival > 23) {
                return new Response("Hours of arrival duration must be a non-negative integer",Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Hours of arrival duration must be selected",Status.BAD_REQUEST);
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
            return new Response("Minutes of scale duration must be selected",Status.BAD_REQUEST);
        }

        LocalDateTime departureDate = LocalDateTime.of(yearInt, monthInt, dayInt, hourInt, minutesInt);
        
        StoragePlane storagePlane = StoragePlane.getInstance();
        Plane planeP = storagePlane.getPlane(plane);
        
        StorageFlight storageFlight = StorageFlight.getInstance();
        Flight flight = storageFlight.getFlight(id);
        if (flight == null) {
                return new Response("Flight not found", Status.NOT_FOUND);
            }

        if(!storageFlight.addFlight(new Flight(id, plane, departureLocation, arrivalLocation, departureDate, hourArrival, minuteArrival))){
            return new Response("These flight already exits", Status.BAD_REQUEST);
        }
            return new Response("Flight created successfully", Status.CREATED);
        } catch (Exception e) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
    private static boolean isValidFlightIdFormat(String id) {
        return id.matches("^[A-Z]{3}\\d{3}$");
    }
    
    public static Response delayFlight(){
        try {
            
        } catch (Exception e) {
            return new Response("Unexpected error", Status.BAD_REQUEST);
        }
        
    }

}
