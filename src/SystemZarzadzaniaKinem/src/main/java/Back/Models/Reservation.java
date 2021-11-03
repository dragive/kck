package Back.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class Reservation {
    private Integer id;
    private Integer userId;
    private Date dateOfReservation;
    private Date dateOfCreation;
    private Integer seansId;

}
