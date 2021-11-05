package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Room;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class RoomsController {
    public Room getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Room> list){ DataBaseService.getInstance().saveAllRoom(list);}
    public List<Room> getAll(){
        return FileDataBaseService.getInstance().getAllRoom();
    }

    public void createNew(Room object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Room object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Room object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
