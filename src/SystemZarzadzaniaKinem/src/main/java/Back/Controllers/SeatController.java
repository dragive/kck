package Back.Controllers;

import Back.Models.Reservation;
import Back.Models.Seat;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class SeatController {
    public Seat getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Seat> getAll(){
        return FileDataBaseService.getInstance().getAllSeat();
    }

    public void createNew(Seat seat){}
    public void update(Seat seat){}
    public void delete(Seat seat){}
}
