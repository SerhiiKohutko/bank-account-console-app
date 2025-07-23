package service.exception;

public class NoAccountFoundWithProvidedUsernameException extends Exception {
    public NoAccountFoundWithProvidedUsernameException(String username) {
        super("Account not found with username:: " + username);
    }
}
