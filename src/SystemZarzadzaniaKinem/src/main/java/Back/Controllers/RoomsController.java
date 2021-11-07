package Back.Controllers;

import Back.Models.Cinema;
import Back.Models.Seat;
import Back.Services.DataBaseService;
import Back.Models.Room;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RoomsController {
    public Room getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Room> list){ DataBaseService.getInstance().saveAllRoom(list);}
    public List<Room> getAll(){return FileDataBaseService.getInstance().getAllRoom();}
    public List<Room> getByCinemaId(Integer cinemaId) {
        List<Room> roomList = new ArrayList<>();
        List<Room> mainList = this.getAll();
        for(Room room:mainList) {
            if(room.getCinemaId().equals(cinemaId)) roomList.add(room);
        }
        return roomList;
    }
    public List<Seat> FillRoom(Integer rows, Integer cols){
        List<Seat> seatList = new ArrayList<>();
        Seat seat;
        Integer roomId;
        List<Room> roomList = this.getAll();
        if(roomList.size()==0) roomId=1;
        else roomId = this.getById(roomList.size()).getId()+1;
        Integer counter=1;
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                seat = new Seat();
                seat.setNumber(counter++);
                seat.setRow(i+1);
                seat.setRoomId(roomId);
                seatList.add(seat);
            }
        }
        return seatList;
    }
    public List<Seat> getSeatsByRoomId(Integer id)
    {
        List<Seat> seatList = new ArrayList<>();
        List<Seat> mainList = this.getById(id).getSeatList();
        for(Seat item:mainList)
        {
            if(item.getRoomId().equals(id)) seatList.add(item);
        }
        return seatList;
    }

    public void createNew(Room object){List list = this.getAll();if(list.size()==0) object.setId(1);else {Room last = (Room)list.get(list.size()-1);object.setId(last.getId()+1);}list.add(object);this.saveAll(list);}
    public void update(Room object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Room object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
