package Back.Controllers;

import Back.Models.Room;
import Back.Models.Seat;
import Back.Services.DataBaseService;
import Back.Models.Seans;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeansController {
    public Seans getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Seans> list){ DataBaseService.getInstance().saveAllSeans(list);}
    public List<Seans> getAll(){
        return FileDataBaseService.getInstance().getAllSeans();
    }
    public List<Seans> getSeansByRoomId(Integer roomId) {
        List<Seans> seansList = new ArrayList<>();
        List<Seans> mainList = this.getAll();
        for(Seans seans:mainList) {
            if(seans.getRoomId().equals(roomId)) seansList.add(seans);
        }
        return seansList;
    }
    public List<Seans> getSeansByCinema(Integer cinemaId) {
        List<Seans> seansList = new ArrayList<>();
        List<Seans> tempList;
        RoomsController roomsController = new RoomsController();
        List<Room> roomList = roomsController.getByCinemaId(cinemaId);

        for(Room room: roomList)
        {
            tempList = this.getSeansByRoomId(room.getId());
            for(Seans seans:tempList)
            {
                seansList.add(seans);
            }
        }
        return seansList;
    }
    public void createNew(Seans object){List list = this.getAll();if(list.size()==0) object.setId(1);else {Seans last = (Seans)list.get(list.size()-1);object.setId(last.getId()+1);}list.add(object);this.saveAll(list);}
    public void update(Seans object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Seans object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
