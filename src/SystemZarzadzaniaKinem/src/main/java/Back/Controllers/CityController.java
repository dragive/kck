package Back.Controllers;

import Back.Models.Cinema;
import Back.Models.City;
import Back.Models.Seans;
import Back.Services.DataBaseService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CityController {

    public List<City> getAll(){
        return DataBaseService.getInstance().getAllCity();
    }
    public void saveAll(List<City> list){ DataBaseService.getInstance().saveAllCity(list);}
    public City getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}

    public void createNew(City object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(City object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(City object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}

}
