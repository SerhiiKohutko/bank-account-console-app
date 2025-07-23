package ui;

import model.Account;
import model.BankCard;
import model.Currency;
import service.BankAccountAuthenticationService;
import service.BankAccountService;
import service.BankCardService;
import service.exception.InvalidPasswordException;
import service.exception.NoAccountFoundWithProvidedUsernameException;
import service.exception.UsernameInUseException;

import java.util.List;
import java.util.Scanner;

public class ConsoleAccountManagementInterface implements AccountManagementInterface {
    private final BankAccountAuthenticationService authenticationService;
    private final BankAccountService service;
    private final BankCardService cardService;
    private final Scanner scanner = new Scanner(System.in);


    public ConsoleAccountManagementInterface(BankAccountAuthenticationService authenticationService, BankAccountService service, BankCardService cardService) {
        this.authenticationService = authenticationService;
        this.service = service;
        this.cardService = cardService;
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
        while (true) {
            System.out.println("1. List all cards\n2. Account details\n3. Logout\n4. Exit");
            String userInput = readInput();
            switch (userInput) {
                case "1" -> listAllUserCards(connectedAccount);
                case "2" -> accountDetails();
                case "3" -> logout();
                case "4" -> exit();
                default -> wrongInput();
            }
        }
    }

    private void listAllUserCards(Account connectedAccount){
        while (true){
            List<BankCard> cardList = connectedAccount.getCardList();
            if (cardList.isEmpty()){
                System.out.println("You have no cards yet.");
                while (true) {
                    System.out.println("1. Create new card\n 2. Back");
                    String userInput = readInput();
                    switch (userInput) {
                        case "1" -> createNewCard(connectedAccount);
                        case "2" -> {
                            return;
                        }
                        default -> wrongInput();
                    }
                }
            } else {
                while (true) {
                    System.out.println("Type 'exit' to get back to main menu:");
                    System.out.println("Choose your card: ");
                    for (int i = 0; i < cardList.size(); i++){
                        BankCard card = cardList.get(i);
                        System.out.println(i + ". " + card.getCardNumber() + ": " + card.getMoneyAmount() + card.getCurrency());
                    }

                    String userInput = readInput().trim();
                    if (userInput.equals("exit")) {
                        return;
                    }

                    int userInputParsed = -1;

                    try {
                        userInputParsed = Integer.parseInt(userInput);
                    } catch (NumberFormatException e) {
                        System.out.println("Choose value between 0 and " + (cardList.size() - 1));
                        continue;
                    }

                    if (userInputParsed > cardList.size() - 1 || userInputParsed < 0) {
                        wrongInput();
                    } else {
                        showCardDetails(cardList.get(userInputParsed));
                    }
                }


            }
        }
    }

    private void createNewCard(Account connectedAccount){
        while (true) {
            System.out.println("Type 'exit' to get back:");
            System.out.println("Choose currency: ");
            for (int i = 1; i <= Currency.values().length; i++){
                System.out.println(i + ". " + Currency.values()[i - 1]);
            }
            String userInput = readInput().trim();
            if (userInput.equals("exit")) {
                return;
            }

            int userInputParsed = -1;

            try {
                userInputParsed = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Choose value between 1 and " + (Currency.values().length));
                continue;
            }

            if (userInputParsed > Currency.values().length || userInputParsed < 0) {
                wrongInput();
            } else {
                BankCard createdCard = cardService.addNewCard(connectedAccount, Currency.values()[userInputParsed - 1]);
                if (createdCard != null) {
                    System.out.println("Card created successfully!");
                    return;
                } else {
                    unpredictedError();
                }
            }
        }
    }

    private void showCardDetails(BankCard card){

    }

    private void accountDetails(){

    }

    private void logout(){

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
