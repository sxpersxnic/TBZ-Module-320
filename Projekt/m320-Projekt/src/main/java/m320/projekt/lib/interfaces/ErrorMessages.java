package m320.projekt.lib.interfaces;

public interface ErrorMessages {
    String USER_NOT_FOUND = "User with ID %s not found";
    String USER_EMAIL_EXISTS = "User with email %s already exists";
    String USER_USERNAME_EXISTS = "User with username %s already exists";
    String USER_INVALID_USERNAME = "Username %s is invalid";
    String USER_INVALID_EMAIL = "Email %s is invalid";
    String USER_INVALID_PASSWORD = "Password %s is invalid";
    String USER_INVALID_ROLE = "Invalid role assignment for user with ID %s";
    String ROLE_NOT_FOUND = "Role with ID %s not found";
    String ROLE_INVALID = "Role %s is invalid";
    String AUTH_INVALID_CREDENTIALS = "Invalid credentials";

}
