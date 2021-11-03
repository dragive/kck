package Back.Controllers;

import Back.Models.Cinema;
import Back.Models.Seans;

import java.util.ArrayList;
import java.util.List;

public class CinemaController {

    public static List<Cinema> getAll(){return new ArrayList<>();}
    public Cinema getById(Integer id){return null;}

    public void createNew(Cinema cinema){}
    public void update(Cinema cinema){}
    public void delete(Cinema cinema){}

    public Cinema searchByAttributes(Cinema cinema){return null;}
    public List<Seans> getSeanses(){ return new ArrayList<Seans>();}
}
