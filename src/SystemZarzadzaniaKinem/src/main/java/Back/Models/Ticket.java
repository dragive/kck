package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Ticket {

    private Integer id;
    private Integer seatId;
    private Integer roomId;
    private Integer seansId;
    private TicketType ticketType;
    private String name;


}
