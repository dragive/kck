package Back.Controllers;


import Back.Services.DataBaseService;
import Back.Models.GiftCard;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class GiftCardController {
    public GiftCard getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<GiftCard> list){ DataBaseService.getInstance().saveAllGiftCard(list);}
    public List<GiftCard> getAll(){
        return FileDataBaseService.getInstance().getAllGiftCard();
    }

    public void createNew(GiftCard object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(GiftCard object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(GiftCard object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
