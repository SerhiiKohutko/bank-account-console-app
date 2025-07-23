package repository;

import model.Account;

import java.util.Optional;

public class InMemoryAccountRepository implements AccountRepository{

    @Override
    public Account saveAccount(Account account) {
        return null;
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
        return Optional.empty();
    }
}
