package web.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class LoginService {

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin123";

    public static boolean login(String username, String password, String dob) {
        if (!isValidCredentials(username, password)) {
            return false;
        }

        if (!isValidAge(dob, 18)) {
            return false;
        }

        return true;
    }

    private static boolean isValidCredentials(String username, String password) {
        if (username == null || password == null) {
            return false;
        }
        return VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password);
    }

    private static boolean isValidAge(String dobStr, int minAge) {
        if (dobStr == null || dobStr.trim().isEmpty()) {
            return false;
        }

        try {
            LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate today = LocalDate.now();
            int age = Period.between(dob, today).getYears();
            return age >= minAge;
        } catch (DateTimeParseException e) {
            return false;   
        }
    }
}