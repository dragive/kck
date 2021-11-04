package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GiftCard {
    private Integer id;
    private String code;
    private Double amount;
}
