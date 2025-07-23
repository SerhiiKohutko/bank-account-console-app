package service.exception;

public class UsernameInUseException extends Exception {
    public UsernameInUseException(String username) {
        super(username + " is already is use.");
    }
}
