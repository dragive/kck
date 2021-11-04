package Back.Controllers;

import Back.Models.Reservation;
import Back.Models.Room;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

//klasy do CRUD na dysku poszczególnych pomieszczeń
public class RoomsController {
    public Room getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Room> getAll(){
        return FileDataBaseService.getInstance().getAllRoom();
    }

    public void createNew(Room room){}
    public void update(Room room){}
    public void delete(Room room){}
}
