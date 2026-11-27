
package com.example.appdev_debugging.data;

import com.example.appdev_debugging.data.model.User;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {

    private final Map<String, User> usersByEmail = new HashMap<>();

    @Override
    public synchronized User findByEmail(String email) {
        if (email == null) return null;
        return usersByEmail.get(normalize(email));
    }

    @Override
    public synchronized boolean exists(String email) {
        return email != null && usersByEmail.containsKey(normalize(email));
    }

    @Override
    public synchronized boolean add(User user) {
        if (user == null || user.getEmail() == null) {
            return false;
        }

        String normalizedEmail = normalize(user.getEmail());

        if (usersByEmail.containsKey(normalizedEmail)) {
            return false;
        }

        usersByEmail.put(normalizedEmail, user);
        return true;
    }

    private static String normalize(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
