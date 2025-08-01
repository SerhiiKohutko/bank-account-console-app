package service;

import model.Account;
import repository.AccountRepository;
import service.exception.InvalidPasswordException;
import service.exception.NoAccountFoundWithProvidedUsernameException;
import service.exception.UsernameInUseException;

public class BankAccountAuthenticationService {
    private final AccountRepository repository;

    public BankAccountAuthenticationService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account login(String username, String password) throws NoAccountFoundWithProvidedUsernameException, InvalidPasswordException {
        Account account = repository.getAccountByUsername(username)
                .orElseThrow(() -> new NoAccountFoundWithProvidedUsernameException(username));

        if (!account.getPassword().equals(password)){
            throw new InvalidPasswordException(username);
        }

        return account;
    }

    public boolean signUp(Account account) throws UsernameInUseException {
        if (repository.getAccountByUsername(account.getUsername()).isPresent()){
            throw new UsernameInUseException(account.getUsername());
        }
        Account savedAccount = repository.saveAccount(account);
        return savedAccount != null;
    }

    public Account changeAccountPassword(){
        return null;
    }
}
