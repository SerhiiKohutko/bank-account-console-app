package service.exception;

public class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String username) {
        super("Invalid password for account with username:: " + username);
    }
}
