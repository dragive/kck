package Back.Controllers;

import Back.Models.Payment;
import Back.Models.Reservation;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    public Reservation getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Reservation> getAll(){
        return FileDataBaseService.getInstance().getAllReservation();
    }

    public void createNew(Reservation reservation){}
    public void update(Reservation reservation){}
    public void delete(Reservation reservation){}
}
