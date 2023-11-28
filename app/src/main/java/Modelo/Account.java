package Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private double balance = 0;
    private int cbu;
    private String alias;
    private List<CreditCard> creditCards;

    public Account (int cbu, String alias) {
        this.cbu = cbu;
        this.alias = alias;
        creditCards = new ArrayList<>();
    }

    public double getBalance() {
        setBalance();
        return balance;
    }

    public void addCreditCard(CreditCard creditCard) {
        creditCards.add(creditCard);
        setBalance();
    }

    public void setBalance() {
        double balance_total = 0;
        for (CreditCard c: creditCards) {
            balance_total += c.getSaldo_actual();
        }
        balance = balance_total;

    }

    public int getCbu() {return cbu;}
    public String getAlias() {return alias;}

    public List<CreditCard> getCreditCards(){return creditCards;}


}
