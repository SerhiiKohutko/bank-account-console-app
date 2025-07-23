package repository;

import model.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository{
    private final Map<String, Account> accounts = new HashMap<>();
    private long idCounter = 1;

    @Override
    public Account saveAccount(Account account) {
        accounts.put(account.getUsername(), account);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {
        return null;
    }

    @Override
    public void deleteAccount(Account account) {

    }

    @Override
    public void deleteAccountById(long id) {

    }

    @Override
    public Account getAccountById(long id) {
        return null;
    }

    @Override
    public Optional<Account> getAccountByUsername(String username) {
        return Optional.ofNullable(accounts.get(username));
    }

    public long getNextId(){
        return idCounter++;
    }
}
