package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Data
public class Reservation {
    private Integer id;
    private Integer userId;
    private Date dateOfReservation;
    private Date dateOfCreation;
    private Integer seansId;
    private Integer seatId;
    private boolean paid;
}
