package Back.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Seans {
    private Integer id;
    private Integer filmId;
    private Date date;
    private Integer cinemaId;

}
