package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.User;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class UsersController {
    public User getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<User> list){ DataBaseService.getInstance().saveAllUser(list);}
    public List<User> getAll(){
        return FileDataBaseService.getInstance().getAllUser();
    }

    public void createNew(User object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(User object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(User object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
