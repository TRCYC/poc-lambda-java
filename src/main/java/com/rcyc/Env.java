package com.rcyc;

import java.util.Map;

/**
 * Env utility class for managing environment variables and application defaults.
 * 
 * Usage:
 * - Always use Env.get("VAR_NAME") to access an environment variable.
 *   This will return the value from the environment if set, or the default if defined below,
 *   or null if not set and no default is defined.
 *
 * Adding New Defaults:
 * - To provide a default value for a variable, simply add it to the DEFAULTS map below, e.g.:
 *     "MY_CUSTOM_VAR", "defaultValue"
 * - You do NOT need to add a separate static field for each variable.
 * - After adding a new default, you can use Env.get("MY_CUSTOM_VAR") throughout your code.
 *
 * Resolution Order:
 * 1. If the environment variable is set, its value is returned.
 * 2. If not, the system property is checked.
 * 3. If neither is set, and a default is defined in DEFAULTS, the default is returned.
 * 4. If not set anywhere and no default exists, returns null.
 *
 * Example:
 *   String stage = Env.get("STAGE"); // Uses env var if set, else default "dev"
 *   String custom = Env.get("MY_CUSTOM_VAR"); // Uses env var if set, else default if defined, else null
 */
public class Env {
    /**
     * Map of environment variable names to their default values.
     * 
     * To add a new default, add a new entry here.
     * e.g. "NEW_VAR", "defaultValue"
     */
    private static final Map<String, String> DEFAULTS = Map.of(
        "LAMBDA_FUNCTION", "dummy"
    );

    // Static initializer: sets any default as a system property if not set in env or sysprops.
    static {
        for (Map.Entry<String, String> entry : DEFAULTS.entrySet()) {
            String key = entry.getKey();
            String envValue = System.getenv(key);
            String sysPropValue = System.getProperty(key);
            // Set default as system property only if not already set in env or sysprops
            if ((envValue == null || envValue.isEmpty()) && (sysPropValue == null || sysPropValue.isEmpty())) {
                System.setProperty(key, entry.getValue());
            }
        }
    }

    /**
     * Retrieves the value of an environment variable.
     * Checks environment variables first, then system properties, then falls back to default if defined.
     * 
     * @param key The name of the variable
     * @return The variable value from the environment, system property, or default, or null if not found
     */
    public static String get(String key) {
        String value = System.getenv(key);
        if (value != null && !value.isEmpty()) {
            return value;
        }
        return System.getProperty(key); // Will be the default if not set elsewhere
    }

    // Prevent instantiation
    private Env() {}
}