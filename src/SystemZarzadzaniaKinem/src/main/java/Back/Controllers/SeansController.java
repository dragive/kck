package Back.Controllers;

import Back.Models.Reservation;
import Back.Models.Seans;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class SeansController {
    public Seans getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Seans> getAll(){
        return FileDataBaseService.getInstance().getAllSeans();
    }

    public void createNew(Seans seans){}
    public void update(Seans seans){}
    public void delete(Seans seans){}
}
