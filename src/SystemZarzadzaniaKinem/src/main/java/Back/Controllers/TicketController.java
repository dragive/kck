package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Ticket;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class TicketController {
    public Ticket getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Ticket> list){ DataBaseService.getInstance().saveAllTicket(list);}
    public List<Ticket> getAll(){
        return FileDataBaseService.getInstance().getAllTicket();
    }

    public void createNew(Ticket object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Ticket object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Ticket object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
