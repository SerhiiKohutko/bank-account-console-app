package repository;

import model.BankCard;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryBankCardRepository implements BankCardRepository{
    private final Map<String, BankCard> cards = new HashMap<>();
    @Override
    public BankCard saveCard(BankCard card) {
        cards.put(card.getCardNumber(), card);
        return card;
    }

    @Override
    public Optional<BankCard> findCardByCardNumber(String generatedCardNumber) {
        return Optional.ofNullable(cards.get(generatedCardNumber));
    }
}
