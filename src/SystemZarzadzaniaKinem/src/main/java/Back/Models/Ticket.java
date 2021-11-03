package Back.Models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Ticket {

    private Integer id;
    private Integer seatId;
    private Integer roomId;
    private Integer seansId;
    private TicketType ticketType;


}
