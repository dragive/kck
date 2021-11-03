package Back.Models;

import lombok.Getter;
import lombok.Setter;

public enum Payment {

    CASH(){

    },
    INTERNET(){

    },
    CARD(){

    };

    @Getter
    @Setter
    private Double amount;

}
