package ui;

import model.Account;
import service.BankAccountAuthenticationService;
import service.BankAccountService;
import service.exception.InvalidPasswordException;
import service.exception.NoAccountFoundWithProvidedUsernameException;
import service.exception.UsernameInUseException;

import java.util.Scanner;

public class ConsoleAccountManagementInterface implements AccountManagementInterface {
    private final BankAccountAuthenticationService authenticationService;
    private final BankAccountService service;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleAccountManagementInterface(BankAccountAuthenticationService authenticationService, BankAccountService service) {
        this.authenticationService = authenticationService;
        this.service = service;
    }

    @Override
    public void start() {
        loginOrSignUp();
    }

    private void loginOrSignUp(){
        while (true) {
            System.out.println("1. Login\n2. Sign Up\n3. Exit\n");
            String userInput = readInput();
            switch (userInput) {
                case "1" -> login();
                case "2" -> signUp();
                case "3" -> exit();
                default -> wrongInput();
            }
        }
    }

    private void login(){
        while (true) {
            System.out.println("Input your credentials:");
            String login = readRequestedProperty("login");
            String password = readRequestedProperty("password");

            Account authneticatedAccount = null;

            try {
                authneticatedAccount = authenticationService.login(login, password);
            } catch (NoAccountFoundWithProvidedUsernameException e) {
                System.out.println("Invalid username! Try again!");
            } catch (InvalidPasswordException e) {
                System.out.println("Invalid password! Try again!");
            } catch (Exception e) {
                unpredictedError();
            }

            if (authneticatedAccount == null) {
                while (true) {
                    System.out.println("1. Login\n 2. Back");
                    String userInput = readInput();
                    if (userInput.equals("1")) {
                        break;
                    } else if (userInput.equals("2")) {
                        return;
                    } else {
                        wrongInput();
                    }
                }
            } else {
                mainMenu(authneticatedAccount);
            }

        }

    }

    private void mainMenu(Account connectedAccount){
        System.out.println("Welcome to main menu, " + connectedAccount.getUsername());
    }

    private void signUp(){
        while (true) {
            System.out.println("Input your credentials:");
            String username = readRequestedProperty("username");
            String firstName = readRequestedProperty("firstName");
            String lastName = readRequestedProperty("lastName");
            String password = passwordConfirmation();

            Account account =
                    new Account.BankAccountBuilder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .username(username)
                    .password(password)
                    .build();

            boolean signUpResult = false;
            try {
                signUpResult = authenticationService.signUp(account);
            } catch (UsernameInUseException e) {
                System.out.println("Username is already in use. Try again");
            } catch (Exception e) {
                unpredictedError();
            }

            if (signUpResult) {
                System.out.println("Account registered successfully!");
                return;
            }
            else unpredictedError();
        }
    }

    private String passwordConfirmation(){
        while (true) {
            String password = readRequestedProperty("password");
            String passwordConfirm = readRequestedProperty("confirm password");
            if (!password.equals(passwordConfirm)) {
                System.out.println("Password don't match! Try again.");
            } else {
                return password;
            }
        }
    }

    private String readRequestedProperty(String propertyName){
        while (true) {
            System.out.println(propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1) + ": ");
            String userInput = readInput();
            if (userInput.isEmpty() || userInput.isBlank()) {
                System.out.println("You should not leave " + propertyName + " empty!");
            } else {
                return userInput;
            }
        }
    }

    private void exit(){
        System.exit(1);
    }

    private void wrongInput(){
        System.out.println("Wrong input! Try again.");
    }

    private void unpredictedError(){
        System.out.println("Unpredicted error occurred. Please, try again.");
    }

    private String readInput(){
        return scanner.nextLine();
    }
}
