package model;

import java.util.List;

public class Account {
    private long id;
    private String username;
    private String password;
    private String ownerFirstName;
    private String ownerLastName;
    private List<BankCard> cardList;

    public Account(String username, String password, String ownerFirstName, String ownerLastName) {
        this.username = username;
        this.password = password;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }

    public List<BankCard> getCardList() {
        return cardList;
    }

    public void setCardList(List<BankCard> cardList) {
        this.cardList = cardList;
    }
}
