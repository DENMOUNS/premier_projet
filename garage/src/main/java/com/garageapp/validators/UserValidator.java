package com.garageapp.validators;

import com.garageapp.garage.model.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class UserValidator {
    public static boolean validateUser(User user) {
        return isValidId(user.getId())
                && isValidName(user.getName())
                && isValidLastName(user.getLastname())
                && isValidPhone(user.getPhone())
                && isValidEmail(user.getEmail())
                && isValidCreatedAt(user.getCreated_at())
                && isValidUpdatedAt(user.getUpdated_at());
    }

    private static boolean isValidId(long id) {
        return id > 0;
    }

    private static boolean isValidName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("Le champ 'name' est obligatoire.");
            return false;
        }
        return true;
    }

    private static boolean isValidLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            System.out.println("Le champ 'lastname' est obligatoire.");
            return false;
        }
        return true;
    }

    private static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            System.out.println("Le champ 'phone' est obligatoire.");
            return false;
        }

        // Vérification du format du numéro de téléphone avec au moins 8 chiffres mais je vais modifier le rejex quand je vais trouver mieux
        if (!Pattern.matches("\\d{8,}", phone)) {
            System.out.println("Le champ 'phone' doit être un numéro de téléphone d'au moins 8 chiffres.");
            return false;
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            System.out.println("Le champ 'email' est obligatoire.");
            return false;
        }
        if (!Pattern.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b", email)) {
            System.out.println("Le champ 'email' doit être une adresse e-mail valide.");
            return false;
        }
        return true;
    }

    private static boolean isValidCreatedAt(String created_at) {
        if (created_at == null || created_at.isEmpty()) {
            System.out.println("Le champ 'date de création' est obligatoire.");
            return false;
        }
        try {
            LocalDate.parse(created_at, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Le champ 'date de création' doit être au format de date valide (ex: dd-MM-yyyy).");
            return false;
        }
        return true;
        
    }
    private static boolean isValidUpdatedAt(String created_at) {
        if (created_at == null || created_at.isEmpty()) {
            System.out.println("Le champ 'date de création' est obligatoire.");
            return false;
        }
        try {
            LocalDate.parse(created_at, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        } catch (DateTimeParseException e) {
            System.out.println("Le champ 'date de mise a jour' doit être au format de date valide (ex: dd-MM-yyyy).");
            return false;
        }
        return true;
        
    }

}
