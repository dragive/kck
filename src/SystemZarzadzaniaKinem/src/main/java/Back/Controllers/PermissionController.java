package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Permission;

import java.util.List;
import java.util.stream.Collectors;

public class PermissionController {
    public Permission getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Permission> list){ DataBaseService.getInstance().saveAllPermission(list);}
    public List<Permission> getAll(){
        return DataBaseService.getInstance().getAllPermission();
    }

    public void createNew(Permission object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(Permission object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Permission object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
