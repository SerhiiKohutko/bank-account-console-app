package repository;

import model.Account;

import java.util.Optional;

public interface AccountRepository {
    Account saveAccount(Account account);
    Account updateAccount(Account account);
    void deleteAccount(Account account);
    void deleteAccountById(long id);
    Account getAccountById(long id);
    Optional<Account> getAccountByUsername(String username);
}
