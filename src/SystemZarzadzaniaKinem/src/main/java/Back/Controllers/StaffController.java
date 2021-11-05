package Back.Controllers;

import Back.Models.Staff;
import Back.Models.User;
import Back.Services.DataBaseService;
import Back.Services.FileDataBaseService;

import java.util.List;
import java.util.stream.Collectors;

public class StaffController {
    public Staff getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Staff> list){ DataBaseService.getInstance().saveAllStaff(list);}
    public List<Staff> getAll(){
        return FileDataBaseService.getInstance().getAllStaff();
    }

    public void createNew(Staff object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Staff object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Staff object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
