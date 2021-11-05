package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.TicketType;

import java.util.List;
import java.util.stream.Collectors;

public class TicketTypeController {
    public TicketType getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<TicketType> list){ DataBaseService.getInstance().saveAllTicketType(list);}
    public List<TicketType> getAll(){
        return DataBaseService.getInstance().getAllTicketType();
    }

    public void createNew(TicketType object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(TicketType object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(TicketType object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
