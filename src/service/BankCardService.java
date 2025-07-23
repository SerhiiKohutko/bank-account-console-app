package service;

import model.Account;
import model.BankCard;
import model.Currency;
import repository.BankCardRepository;

import java.util.Random;

public class BankCardService {

    private final BankCardRepository repository;

    public BankCardService(BankCardRepository repository) {
        this.repository = repository;
    }

    public BankCard addNewCard(Account account, Currency currency) {
        BankCard card = new BankCard(currency);
        while (true) {
            String generatedCardNumber = generateCardNumber();
            if (repository.findCardByCardNumber(generatedCardNumber).isPresent()) {
                generatedCardNumber = generateCardNumber();
            } else {
                card.setCardNumber(generateCardNumber());
                break;
            }
        }
        card.setMoneyAmount(0);
        account.getCardList().add(card);
        return repository.saveCard(card);
    }

    private String generateCardNumber(){
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++){
            sb.append(random.nextInt() % 10);
        }
        return sb.toString();
    }
}
