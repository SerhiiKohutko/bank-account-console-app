package service;

import model.Account;
import repository.AccountRepository;

public class BankAccountService {
    private final AccountRepository repository;

    public BankAccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account createAccount(){
        return null;
    }

    public Account changeAccountPassword(){
        return null;
    }

    public Account updateAccountDetails(){
        return null;
    }

    public void deleteAccount(){

    }
}
