
package com.example.appdev_debugging;

import android.content.Context;
import android.util.Log;

import com.example.appdev_debugging.auth.AuthManager;
import com.example.appdev_debugging.auth.AuthResult;
import com.example.appdev_debugging.data.InMemoryUserRepository;
import com.example.appdev_debugging.data.UserRepository;
import com.example.appdev_debugging.session.SessionManager;

public final class ServiceLocator {

    public static final String DEMO_NAME = "Demo User";
    public static final String DEMO_EMAIL = "demo@example.com";
    public static final String DEMO_PASSWORD = "Password123";

    private static AuthManager authManager;
    private static SessionManager sessionManager;

    private ServiceLocator() {
    }

    public static synchronized AuthManager getAuthManager() {

        if (authManager == null) {

            authManager = new AuthManager(provideRepository());

            // Register the demo account.
            AuthResult result = authManager.register(
                    DEMO_NAME,
                    DEMO_EMAIL,
                    DEMO_PASSWORD,
                    DEMO_PASSWORD
            );

            Log.d("DEBUG_AUTH",
                    "Demo registration successful: " + result.isSuccess());

            Log.d("DEBUG_AUTH",
                    "Demo registration error: " + result.getErrorMessage());
        }

        return authManager;
    }

    public static synchronized SessionManager getSessionManager(Context context) {

        if (sessionManager == null) {
            sessionManager = new SessionManager(
                    context.getApplicationContext()
            );
        }

        return sessionManager;
    }

    private static UserRepository provideRepository() {
        return new InMemoryUserRepository();
    }
}
