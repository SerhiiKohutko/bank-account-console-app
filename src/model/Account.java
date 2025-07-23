package model;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String username;
    private String password;
    private String ownerFirstName;
    private String ownerLastName;
    private List<BankCard> cardList = new ArrayList<>();

    private Account(String username, String password, String ownerFirstName, String ownerLastName) {
        this.username = username;
        this.password = password;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
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

    public static class BankAccountBuilder {
        private String username;
        private String password;
        private String ownerFirstName;
        private String ownerLastName;

        public BankAccountBuilder username(String username){
            this.username = username;
            return this;
        }

        public BankAccountBuilder password(String password){
            this.password = password;
            return this;
        }

        public BankAccountBuilder firstName(String firstName){
            ownerFirstName = firstName;
            return this;
        }

        public BankAccountBuilder lastName(String lastName){
            ownerLastName = lastName;
            return this;
        }

        public Account build(){
            return new Account(username, password, ownerFirstName, ownerLastName);
        }

    }
}
