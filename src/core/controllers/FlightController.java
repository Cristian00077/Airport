package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class FlightController {

   public static Response createFlight(
        String id,
        String plane,
        String departureLocation,
        String scaleLocation,
        String arrivalLocation,
        String departureDate,
        String hoursDurationArrival,
        String minutesDurationArrival,
        String hoursDurationScale,
        String minutesDurationScale) {

    try {
        if (id == null || id.isEmpty()) {
            return new Response("Id must be not empty", Status.BAD_REQUEST);
        }
        if (!isValidFlightId(id)) {
            return new Response(
                "Id format must have the format CCC-DDD (C - Character, D - Digit)",
                Status.BAD_REQUEST
            );
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

        if (departureDate == null || departureDate.isEmpty()) {
            return new Response("Departure date must be not empty", Status.BAD_REQUEST);
        } else {
            try {
                java.time.LocalDate.parse(departureDate);
            } catch (java.time.format.DateTimeParseException e) {
                return new Response("Departure date must be in format YYYY-MM-DD",Status.BAD_REQUEST);
            }
        }

        if (hoursDurationArrival == null || hoursDurationArrival.isEmpty()) {
            return new Response("Hours of arrival duration must be not empty", Status.BAD_REQUEST);
        }
        int hArr;
        try {
            hArr = Integer.parseInt(hoursDurationArrival);
            if (hArr < 0) {
                return new Response("Hours of arrival duration must be a non-negative integer",Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Hours of arrival duration must be a valid integer",Status.BAD_REQUEST);
        }

        if (minutesDurationArrival == null || minutesDurationArrival.isEmpty()) {
            return new Response("Minutes of arrival duration must be not empty", Status.BAD_REQUEST);
        }
        int mArr;
        try {
            mArr = Integer.parseInt(minutesDurationArrival);
            if (mArr < 0 || mArr > 59) {
                return new Response(
                    "Minutes of arrival duration must be between 0 and 59",
                    Status.BAD_REQUEST
                );
            }
        } catch (NumberFormatException e) {
            return new Response("Minutes of arrival duration must be a valid integer",Status.BAD_REQUEST);
        }

        if (hoursDurationScale == null || hoursDurationScale.isEmpty()) {
            return new Response("Hours of scale duration must be not empty", Status.BAD_REQUEST);
        }
        int hScale;
        try {
            hScale = Integer.parseInt(hoursDurationScale);
            if (hScale < 0) {
                return new Response("Hours of scale duration must be a non-negative integer",Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Hours of scale duration must be a valid integer",Status.BAD_REQUEST);
        }

        if (minutesDurationScale == null || minutesDurationScale.isEmpty()) {
            return new Response("Minutes of scale duration must be not empty", Status.BAD_REQUEST);
        }
        int mScale;
        try {
            mScale = Integer.parseInt(minutesDurationScale);
            if (mScale < 0 || mScale > 59) {
                return new Response("Minutes of scale duration must be between 0 and 59",Status.BAD_REQUEST);
            }
        } catch (NumberFormatException e) {
            return new Response("Minutes of scale duration must be a valid integer",Status.BAD_REQUEST);
        }

    } catch (Exception e) {
        return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }

    return null;
}

    
    private static boolean isValidFlightId(String id) {
        if (id == null || id.length() != 6) {
            return false;
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(id.charAt(i))) {
                return false;
            }
        }
        for (int i = 3; i < 6; i++) {
            if (!Character.isDigit(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
