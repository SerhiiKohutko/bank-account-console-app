package service;

import model.Account;
import model.BankCard;
import model.Currency;
import repository.AccountRepository;

import java.util.Random;

public class BankAccountService {
    private final AccountRepository repository;

    public BankAccountService(AccountRepository repository) {
        this.repository = repository;
    }


    public Account updateAccountDetails(){
        return null;
    }

    public void deleteAccount(){

    }


}
