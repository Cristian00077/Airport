package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.storage.StoragePassenger;
import java.time.LocalDate;

public class PassengerController {

    public static Response registerPassenger(String id, String firstname, String lastname,
            String year, String month, String day, String countryPhoneCode, String phone, String country) {
        try {
            long idLong;
            int yearInt;
            int monthInt;
            int dayInt;
            int countryPhoneCodeInt;
            long phoneLong;
            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be not negative", Status.BAD_REQUEST);
                }
                if (id.length() > 15) {
                    return new Response("Id must have less than 15 digits", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                if (id.equals("")) {
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                yearInt = Integer.parseInt(year);
                monthInt = Integer.parseInt(month);
                dayInt = Integer.parseInt(day);
                if (yearInt > 2024) {
                    return new Response("Year invalid", Status.BAD_REQUEST);
                }
                if (year.length() > 4) {
                    return new Response("Date of year invalid", Status.BAD_REQUEST);
                }

                if (monthInt < 1 & monthInt > 12) {
                    return new Response("Month invalid", Status.BAD_REQUEST);
                }
                if (dayInt < 1 & dayInt > 31) {
                    return new Response("Day invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (year.equals("")) {
                    return new Response("Year must be not empty", Status.BAD_REQUEST);
                }
                
                return new Response("Birthdate must be just numeric", Status.BAD_REQUEST);
            }

            try {
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCode);
                if (countryPhoneCode.length() > 3) {
                    return new Response("Country phone code must have 3 or less than 3 digits", Status.BAD_REQUEST);
                }
                if (countryPhoneCodeInt < 0) {
                    return new Response("Country phone code must be greater than 0", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                if (countryPhoneCode.equals("")) {
                    return new Response("Country phone code must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Country phone code must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneLong = Long.parseLong(phone);
                if (phoneLong < 0) {
                    return new Response("Phone must be greater than 0", Status.BAD_REQUEST);
                }
                if (phone.length() > 11) {
                    return new Response("Phone must have less than 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                if (phone.equals("")) {
                    return new Response("Phone must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Phone number must be numeric", Status.BAD_REQUEST);
            }

            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }

            LocalDate birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            StoragePassenger storagePass = StoragePassenger.getInstance();
            if (!storagePass.addPassenger(new Passenger(idLong, firstname, lastname, birthDate,
                    countryPhoneCodeInt, phoneLong, country))) {
                return new Response("Passenger with that id already exists", Status.BAD_REQUEST);
            }
            return new Response("Passenger registered successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
    
    public static Response updatePassenger(String id, String firstname, String lastname,
            String year, String month, String day, String countryPhoneCode, String phone, String country) {
        try {
            long idLong;
            int yearInt;
            int monthInt;
            int dayInt;
            int countryPhoneCodeInt;
            long phoneLong;
            try {
                idLong = Long.parseLong(id);
                if (idLong < 0) {
                    return new Response("Id must be not negative", Status.BAD_REQUEST);
                }
                if (id.length() > 15) {
                    return new Response("Id must have less than 15 digits", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                if (id.equals("")) {
                    return new Response("Id must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (firstname.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            if (lastname.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                yearInt = Integer.parseInt(year);
                monthInt = Integer.parseInt(month);
                dayInt = Integer.parseInt(day);
                if (yearInt > 2024) {
                    return new Response("Year invalid", Status.BAD_REQUEST);
                }
                if (year.length() > 4) {
                    return new Response("Date of year invalid", Status.BAD_REQUEST);
                }

                if (monthInt < 1 & monthInt > 12) {
                    return new Response("Month invalid", Status.BAD_REQUEST);
                }
                if (dayInt < 1 & dayInt > 31) {
                    return new Response("Day invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                if (year.equals("")) {
                    return new Response("Year must be not empty", Status.BAD_REQUEST);
                }
                
                return new Response("Birthdate must be just numeric", Status.BAD_REQUEST);
            }

            try {
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCode);
                if (countryPhoneCode.length() > 3) {
                    return new Response("Country phone code must have 3 or less than 3 digits", Status.BAD_REQUEST);
                }
                if (countryPhoneCodeInt < 0) {
                    return new Response("Country phone code must be greater than 0", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                if (countryPhoneCode.equals("")) {
                    return new Response("Country phone code must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Country phone code must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneLong = Long.parseLong(phone);
                if (phoneLong < 0) {
                    return new Response("Phone must be greater than 0", Status.BAD_REQUEST);
                }
                if (phone.length() > 11) {
                    return new Response("Phone must have less than 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                if (phone.equals("")) {
                    return new Response("Phone must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Phone number must be numeric", Status.BAD_REQUEST);
            }

            if (country.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }

            LocalDate birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            StoragePassenger storagePass = StoragePassenger.getInstance();
            Passenger passenger = storagePass.getPassenger(idLong);
            
            if(passenger == null){
                return new Response("Passenger not found", Status.BAD_REQUEST);
            }else{
                passenger.setFirstname(firstname);
                passenger.setLastname(lastname);
                passenger.setBirthDate(birthDate);
                passenger.setCountryPhoneCode(countryPhoneCodeInt);
                passenger.setPhone(phoneLong);
                passenger.setCountry(country);
                return new Response("Passenger info was updated successfully", Status.OK);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
        
    
}
