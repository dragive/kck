package Back.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Seat {
    private Integer id;
    private Integer roomId;
    private Integer number;
    private Integer row;
}
