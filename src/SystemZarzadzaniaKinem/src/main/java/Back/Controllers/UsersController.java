package Back.Controllers;

import Back.Models.Reservation;
import Back.Models.User;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class UsersController {
    public User getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<User> getAll(){
        return FileDataBaseService.getInstance().getAllUser();
    }

    public void createNew(User user){}
    public void update(User user){}
    public void delete(User user){}
}
