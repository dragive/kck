package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Items;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class ItemsController {
    public Items getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Items> list){ DataBaseService.getInstance().saveAllItems(list);}
    public List<Items> getAll(){
        return FileDataBaseService.getInstance().getAllItems();
    }

    public void createNew(Items object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Items object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Items object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
