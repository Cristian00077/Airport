package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;

public class LocationController {

    public static Response createLocation(String id, String name, String city, String country, double latitude, double longitude) {
        try {
            String latitudeS = String.valueOf(latitude);
            String longitudeS = String.valueOf(longitude);

            try {
                if (!ValidateId(id)) {
                    return new Response("Id must be 3 capital letters ", Status.BAD_REQUEST);
                }
            } catch (Exception e) {
                if (id.equals("")) {
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
            }

            if (name.equals("")) {
                return new Response("Name must be not empty", Status.BAD_REQUEST);
            }

            if (city.equals("")) {
                return new Response("City must be not empty", Status.BAD_REQUEST);
            }
            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }
            try {

                if (!latitudeS.matches("-?\\d+(\\.\\d{1,4})?")) {
                    return new Response("Latitude must have max 4 decimals", Status.BAD_REQUEST);
                }
                if (latitude < -90 && latitude > 90) {
                    return new Response("Latitude is in incorrect range", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (latitudeS.equals("")) {
                    return new Response("Latitude must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Latitude must be numeric", Status.BAD_REQUEST);
            }

            try {

                if (!longitudeS.matches("-?\\d+(\\.\\d{1,4})?")) {
                    return new Response("Longitude must have max 4 decimals", Status.BAD_REQUEST);
                }
                if (longitude < -180 && longitude > 180) {
                    return new Response("Longitude is in incorrect range", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (longitudeS.equals("")) {
                    return new Response("Longitude must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Longitude must be numeric", Status.BAD_REQUEST);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
    
    private static boolean ValidateId(String id) {
        if (id.equals("") && id.length() != 3) {
            return false;
        }
        for (int i = 0; i < 3; i++) {
            if (!Character.isUpperCase(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
