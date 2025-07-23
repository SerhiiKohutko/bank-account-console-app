import repository.AccountRepository;
import repository.BankCardRepository;
import repository.InMemoryAccountRepository;
import repository.InMemoryBankCardRepository;
import service.BankAccountAuthenticationService;
import service.BankAccountService;
import service.BankCardService;
import ui.AccountManagementInterface;
import ui.ConsoleAccountManagementInterface;

public class AccountMainController {
    public static void main(String[] args) {
        AccountRepository accountRepository = new InMemoryAccountRepository();
        BankAccountAuthenticationService authenticationService = new BankAccountAuthenticationService(accountRepository);
        BankAccountService bankAccountService = new BankAccountService(accountRepository);
        BankCardRepository cardRepository = new InMemoryBankCardRepository();
        BankCardService cardService = new BankCardService(cardRepository);
        AccountManagementInterface accountManagementInterface = new ConsoleAccountManagementInterface(authenticationService, bankAccountService, cardService);
        accountManagementInterface.start();
    }
}
