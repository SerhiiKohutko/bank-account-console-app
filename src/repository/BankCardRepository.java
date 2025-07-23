package repository;

import model.BankCard;

import java.util.Optional;

public interface BankCardRepository {
    BankCard saveCard(BankCard card);

    Optional<BankCard> findCardByCardNumber(String generatedCardNumber);
}
