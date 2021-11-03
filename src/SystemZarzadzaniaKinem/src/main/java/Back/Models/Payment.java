package Back.Models;

import lombok.Getter;

@Getter
public enum Payment {
    CASH("Płatność Gotówką"),
    INTERNET("Płatność internetowa"),
    CARD("Płatność kartą");

    private Double amount;
    private String name;

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    Payment(String name){
        this.name = name;
    }
}
