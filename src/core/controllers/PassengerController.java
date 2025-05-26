package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Passenger;
import core.models.SOLID.PassengerAgeCalculator;
import core.models.SOLID.PassengerCalAge;
import core.models.SOLID.PassengerFullPhone;
import core.models.SOLID.PassengerPhoneFormat;
import core.models.storage.StoragePassenger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.swing.JComboBox;
import pattern.observer.EventListener;
import pattern.observer.GenericPublisher;
import pattern.observer.Subscriber;

public class PassengerController {

    private static PassengerAgeCalculator agePass = new PassengerCalAge();
    private static PassengerPhoneFormat fullPhone = new PassengerFullPhone();
    private static final GenericPublisher publisher = new GenericPublisher();
    
    public static void subscribe(EventListener s) {
        publisher.Subscribe(s);
    }

    public static void notifyChanges() {
         
        publisher.NotifySubscribers();
    }
    
    public static void unsubscribe(EventListener s){
        publisher.Unsubscribe(s);
    }
    
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
                if (year.length() > 4) {
                    return new Response("Date of year invalid", Status.BAD_REQUEST);
                }
                if (yearInt > LocalDateTime.now().getYear()) {
                    return new Response("Invalid year", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException e) {
                if (year.equals("")) {
                    return new Response("Year must be not empty", Status.BAD_REQUEST);
                }
                
                return new Response("Year must be just numeric", Status.BAD_REQUEST);
            }
            try {
                monthInt = Integer.parseInt(month);
                if (monthInt > 12) {
                    return new Response("Month invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Month must be selected", Status.BAD_REQUEST);
            }
            try {
                dayInt = Integer.parseInt(day);
                if (dayInt > 31) {
                    return new Response("Month invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Day must be selected", Status.BAD_REQUEST);
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
            notifyChanges();
            return new Response("Passenger registered successfully", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
    
    public static Response getPassenger(String id) {
    try {
        long idLong;
        try {
            idLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return new Response("Id must be numeric", Status.BAD_REQUEST);
        }
        
        StoragePassenger storagePass = StoragePassenger.getInstance();
        Passenger passenger = storagePass.getPassenger(idLong);
        
        if(passenger == null) {
            return new Response("Passenger not found", Status.NOT_FOUND);
        } else {
            return new Response("Passenger found", Status.OK, passenger);
        }
    } catch (Exception ex) {
        return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
    }
}
    
    public static Response updatePassenger(String id, String firstnameN, String lastnameN,
            String yearN, String monthN, String dayN, String countryPhoneCodeN, String phoneN, String countryN) {
        try {
            long idLong;
            int yearInt;
            int monthInt;
            int dayInt;
            int countryPhoneCodeInt;
            long phoneLong;
            
            idLong = Long.parseLong(id);

            if (firstnameN.equals("")) {
                return new Response("Firstname must be not empty", Status.BAD_REQUEST);
            }
            if (lastnameN.equals("")) {
                return new Response("Lastname must be not empty", Status.BAD_REQUEST);
            }
            
            try {
                yearInt = Integer.parseInt(yearN);
                if (yearN.length() > 4) {
                    return new Response("Date of year invalid", Status.BAD_REQUEST);
                }
                if (yearInt > LocalDateTime.now().getYear()) {
                    return new Response("Invalid year", Status.BAD_REQUEST);
                }
                
            } catch (NumberFormatException e) {
                if (yearN.equals("")) {
                    return new Response("Year must be not empty", Status.BAD_REQUEST);
                }
                
                return new Response("Year must be just numeric", Status.BAD_REQUEST);
            }
            try {
                monthInt = Integer.parseInt(monthN);
                if (monthInt > 12) {
                    return new Response("Month invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Month must be selected", Status.BAD_REQUEST);
            }
            try {
                dayInt = Integer.parseInt(dayN);
                if (dayInt > 31) {
                    return new Response("Month invalid", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException e) {
                return new Response("Day must be selected", Status.BAD_REQUEST);
            }

            try {
                countryPhoneCodeInt = Integer.parseInt(countryPhoneCodeN);
                if (countryPhoneCodeN.length() > 3) {
                    return new Response("Country phone code must have 3 or less than 3 digits", Status.BAD_REQUEST);
                }
                if (countryPhoneCodeInt < 0) {
                    return new Response("Country phone code must be greater than 0", Status.BAD_REQUEST);
                }

            } catch (NumberFormatException e) {
                if (countryPhoneCodeN.equals("")) {
                    return new Response("Country phone code must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Country phone code must be numeric", Status.BAD_REQUEST);
            }

            try {
                phoneLong = Long.parseLong(phoneN);
                if (phoneLong < 0) {
                    return new Response("Phone must be greater than 0", Status.BAD_REQUEST);
                }
                if (phoneN.length() > 11) {
                    return new Response("Phone must have less than 11 digits", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                if (phoneN.equals("")) {
                    return new Response("Phone must be not empty", Status.BAD_REQUEST);
                }
                return new Response("Phone number must be numeric", Status.BAD_REQUEST);
            }

            if (countryN.equals("")) {
                return new Response("Country must be not empty", Status.BAD_REQUEST);
            }

            LocalDate birthDate = LocalDate.of(yearInt, monthInt, dayInt);
            StoragePassenger storagePass = StoragePassenger.getInstance();
            Passenger passenger = storagePass.getPassenger(idLong);
            
            if(passenger == null){
                return new Response("Passenger not found", Status.BAD_REQUEST);
            }else{
                passenger.setFirstname(firstnameN);
                passenger.setLastname(lastnameN);
                passenger.setBirthDate(birthDate);
                passenger.setCountryPhoneCode(countryPhoneCodeInt);
                passenger.setPhone(phoneLong);
                passenger.setCountry(countryN);
                notifyChanges();
                return new Response("Passenger info was updated successfully", Status.OK);
            }

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }  
   
    public static void loadPassengersIdIntoComboBox(JComboBox<String> comboBox) {
        StoragePassenger storage = StoragePassenger.getInstance();
        for (Passenger passenger : storage.orderPassengers()) {
            comboBox.addItem(String.valueOf(passenger.getId()));
        }
    }
    
    public static int ageCalculated(Passenger passenger){
        return agePass.calculateAge(passenger);
    }
    public static String fullPhoneFormat(Passenger passenger){
        return fullPhone.generateFullPhone(passenger);
    }
}
