package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Reservation;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationController {
    public Reservation getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Reservation> list){ DataBaseService.getInstance().saveAllReservation(list);}
    public List<Reservation> getAll(){
        return FileDataBaseService.getInstance().getAllReservation();
    }
    public List<Reservation> getReservationBySeansId(Integer id)
    {
        List<Reservation> reservationList = new ArrayList<>();
        List<Reservation> mainList = this.getAll();
        for(Reservation item:mainList)
        {
            if(item.getSeansId().equals(id)) reservationList.add(item);
        }
        return reservationList;
    }
    public List<Reservation> getReservationByUserId(Integer id)
    {
        List<Reservation> reservationList = new ArrayList<>();
        List<Reservation> mainList = this.getAll();
        for(Reservation item:mainList)
        {
            if(item.getUserId().equals(id)) reservationList.add(item);
        }
        return reservationList;
    }
    public void createNew(Reservation object){List list = this.getAll();if(list.size()==0) object.setId(1);else {Reservation last = (Reservation)list.get(list.size()-1);object.setId(last.getId()+1);}list.add(object);this.saveAll(list);}
    public void update(Reservation object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Reservation object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
