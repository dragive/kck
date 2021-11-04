package Back.Models;

import lombok.Getter;

@Getter
public enum Payment {
    CASH(0,"Płatność Gotówką"),
    INTERNET(1,"Płatność internetowa"),
    CARD(2,"Płatność kartą");

    private Double amount;
    private String name;
    private Integer id;

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    Payment(Integer id,String name){
        this.name = name;
        this.id = id;
    }
}
