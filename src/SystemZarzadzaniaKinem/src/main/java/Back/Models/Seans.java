package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class Seans {
    private Integer id;
    private Integer filmId;
    private Date date;
    private Integer roomId;

}
