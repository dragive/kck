package Back.Models;

import java.util.SplittableRandom;

public enum TicketType {
    NORMAL(0,"Normalny"),REDUCED(1,"Ulgowy");
    private String name;
    private Integer id;

    TicketType(Integer id,String name){
        this.id = id;
        this.name = name;
    }
}
