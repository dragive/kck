package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Cinema;
import Back.Models.Seans;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CinemaController {

    public List<Cinema> getAll(){
        return DataBaseService.getInstance().getAllCinema();
    }
    public void saveAll(List<Cinema> list){ DataBaseService.getInstance().saveAllCinema(list);}
    public Cinema getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}

    public void createNew(Cinema object){List list = this.getAll();if(list.size()==0) object.setId(1);else {Cinema last = (Cinema)list.get(list.size()-1);object.setId(last.getId()+1);}list.add(object);this.saveAll(list);}
    public void update(Cinema object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Cinema object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}

    public Cinema searchByAttributes(Cinema object){return null;}
    public List<Seans> getSeanses(){ return new ArrayList<Seans>();}
}
