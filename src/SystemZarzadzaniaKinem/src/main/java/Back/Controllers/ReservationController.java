package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Reservation;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class ReservationController {
    public Reservation getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Reservation> list){ DataBaseService.getInstance().saveAllReservation(list);}
    public List<Reservation> getAll(){
        return FileDataBaseService.getInstance().getAllReservation();
    }

    public void createNew(Reservation object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Reservation object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Reservation object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
