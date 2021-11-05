package Back.Models;

import lombok.Data;
import lombok.Getter;

@Data
public class Payment {
//    CASH(0,"Płatność Gotówką"),
//    INTERNET(1,"Płatność internetowa"),
//    CARD(2,"Płatność kartą");

    private Double amount;
    private String name;
    private Integer id;

}
