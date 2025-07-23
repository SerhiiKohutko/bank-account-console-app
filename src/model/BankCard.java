package model;

public class BankCard {
    private String cardNumber;
    private double moneyAmount;
    private Currency currency;

    public BankCard(Currency currency) {
        this.currency = currency;
    }
}
