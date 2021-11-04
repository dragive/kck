package Back.Controllers;

import Back.Models.GiftCard;
import Back.Models.Items;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class ItemsController {
    public Items getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Items> getAll(){
        return FileDataBaseService.getInstance().getAllItems();
    }

    public void createNew(Items items){}
    public void update(Items items){}
    public void delete(Items items){}
}
