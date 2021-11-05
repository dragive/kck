package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Seans;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class SeansController {
    public Seans getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Seans> list){ DataBaseService.getInstance().saveAllSeans(list);}
    public List<Seans> getAll(){
        return FileDataBaseService.getInstance().getAllSeans();
    }

    public void createNew(Seans object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Seans object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Seans object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
