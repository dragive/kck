package Back.Controllers;

import Back.Models.Cinema;
import Back.Models.Seans;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class CinemaController {

    public List<Cinema> getAll(){
        return FileDataBaseService.getInstance().getAllCinema();
    }
    public Cinema getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}

    public void createNew(Cinema cinema){}
    public void update(Cinema cinema){}
    public void delete(Cinema cinema){}

    public Cinema searchByAttributes(Cinema cinema){return null;}
    public List<Seans> getSeanses(){ return new ArrayList<Seans>();}
}
