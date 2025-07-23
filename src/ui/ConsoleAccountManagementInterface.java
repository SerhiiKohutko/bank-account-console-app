package ui;

import model.Account;
import service.BankAccountAuthenticationService;
import service.BankAccountService;
import service.exception.InvalidPasswordException;
import service.exception.NoAccountFoundWithProvidedUsernameException;

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
            System.out.println("Login: ");
            String login = readInput();
            if (login.isEmpty() || login.isBlank()){
                System.out.println("You should not leave login empty!");
            }
            System.out.println("Password: ");
            String password = readInput();
            if (password.isEmpty() || password.isBlank()){
                System.out.println("You should not leave password empty!");
            }
            Account authneticatedAccount = null;

            try {
                authneticatedAccount = authenticationService.login(login, password);
            } catch (NoAccountFoundWithProvidedUsernameException e) {
                System.out.println("Invalid username! Try again!");
            } catch (InvalidPasswordException e) {
                System.out.println("Invalid password! Try again!");
            } catch (Exception e) {
                System.out.println("Unpredicted error occurred. Please, try again.");
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
                mainMenu();
            }

        }

    }

    private void mainMenu(){
        System.out.println("Welcome to main menu");
    }

    private void signUp(){

    }

    private void exit(){
        System.exit(1);
    }

    private void wrongInput(){
        System.out.println("Wrong input! Try again.");
    }

    private String readInput(){
        return scanner.nextLine();
    }
}
