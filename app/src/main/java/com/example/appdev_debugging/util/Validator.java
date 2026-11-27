package com.example.appdev_debugging.util;

import java.util.regex.Pattern;

public class Validator {

    public static final int MIN_PASSWORD_LENGTH = 8;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    private Validator() {
    }

    public static String validateFullName(String name) {
        if (isBlank(name)) return "Full name is required";
        if (name.trim().length() < 2) return "Full name is too short";
        return null;
    }

    public static String validateEmail(String email) {
        if (isBlank(email)) return "Email is required";

        String normalizedEmail = email.trim().toLowerCase();

        if (!EMAIL_PATTERN.matcher(normalizedEmail).matches()) {
            return "Enter a valid email address";
        }

        String domain = normalizedEmail.substring(
                normalizedEmail.lastIndexOf('@') + 1
        );

        if (domain.endsWith(".ph")) {
            return "Email addresses with .ph domains are not allowed";
        }

        return null;
    }

    public static String validateNewPassword(String password) {

        if (password == null || password.isEmpty()) {
            return "Password is required";
        }

        if (password.length() < MIN_PASSWORD_LENGTH) {
            return "Password must be at least 8 characters";
        }

        boolean hasLetter = false;
        boolean hasDigit = false;

        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                hasLetter = true;
            }

            if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        if (!(hasLetter && hasDigit)) {
            return "Password must contain letters and numbers";
        }

        return null;
    }

    public static String validateLoginPassword(String password) {
        if (password == null || password.isEmpty()) {
            return "Password is required";
        }

        return null;
    }

    public static String validatePasswordConfirmation(
            String password, String confirm) {

        if (confirm == null || confirm.isEmpty()) {
            return "Please confirm your password";
        }

        if (!confirm.equals(password)) {
            return "Passwords do not match";
        }

        return null;
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
