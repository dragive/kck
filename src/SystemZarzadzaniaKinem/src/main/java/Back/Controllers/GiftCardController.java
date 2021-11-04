package Back.Controllers;


import Back.Models.FilmCategory;
import Back.Models.GiftCard;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class GiftCardController {
    public GiftCard getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<GiftCard> getAll(){
        return FileDataBaseService.getInstance().getAllGiftCard();
    }

    public void createNew(GiftCard giftCard){}
    public void update(GiftCard giftCard){}
    public void delete(GiftCard giftCard){}
}
