package config;

public class session {

    private static String userId;
    private static String firstName;
    private static String lastName;
    private static String username;
    private static String email;

    public static void setSession(String userId, String firstName, String lastName, String username, String email) {
        session.userId = userId;
        session.firstName = firstName;
        session.lastName = lastName;
        session.username = username;
        session.email = email;
    }

    public static String getUserId() {
        return userId;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getUsername() {
        return username;
    }

    public static String getEmail() {
        return email;
    }

    public static void clearSession() {
        session.userId = null;
        session.firstName = null;
        session.lastName = null;
        session.username = null;
        session.email = null;
    }
} 
