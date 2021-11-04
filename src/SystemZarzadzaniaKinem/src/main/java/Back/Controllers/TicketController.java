package Back.Controllers;

import Back.Models.Reservation;
import Back.Models.Ticket;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class TicketController {
    public Ticket getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Ticket> getAll(){
        return FileDataBaseService.getInstance().getAllTicket();
    }

    public void createNew(Ticket ticket){}
    public void update(Ticket ticket){}
    public void delete(Ticket ticket){}
}
