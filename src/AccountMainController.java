import repository.AccountRepository;
import repository.InMemoryAccountRepository;
import service.BankAccountAuthenticationService;
import service.BankAccountService;
import ui.AccountManagementInterface;
import ui.ConsoleAccountManagementInterface;

public class AccountMainController {
    public static void main(String[] args) {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        BankAccountAuthenticationService authenticationService = new BankAccountAuthenticationService(accountRepository);
        BankAccountService bankAccountService = new BankAccountService(accountRepository);
        AccountManagementInterface accountManagementInterface = new ConsoleAccountManagementInterface(authenticationService, bankAccountService);
        accountManagementInterface.start();
    }
}
