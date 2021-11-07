package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Seat {
    private Integer roomId;
    private Integer number;
    private Integer row;
}
