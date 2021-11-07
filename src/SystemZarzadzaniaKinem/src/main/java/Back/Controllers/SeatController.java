package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Seat;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class SeatController {
    public Seat getByNumber(Integer id){return this.getAll().stream().filter(o->{return o.getNumber().equals(id);}).findFirst().get();}
    public void saveAll(List<Seat> list){ DataBaseService.getInstance().saveAllSeat(list);}
    public List<Seat> getAll(){
        return FileDataBaseService.getInstance().getAllSeat();
    }

    public void createNew(Seat object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Seat object){this.saveAll(this.getAll().stream().map((o)->{if(o.getNumber() == object.getNumber())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Seat object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
