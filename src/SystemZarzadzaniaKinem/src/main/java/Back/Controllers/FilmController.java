package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.Film;

import java.util.List;
import java.util.stream.Collectors;

public class FilmController {
    public Film getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public void saveAll(List<Film> list){ DataBaseService.getInstance().saveAllFilm(list);}
    public List<Film> getAll(){
        return DataBaseService.getInstance().getAllFilm();
    }

    public void createNew(Film object){List list = this.getAll();if(list.size()==0) object.setId(1);else {Film last = (Film)list.get(list.size()-1);object.setId(last.getId()+1);}list.add(object);this.saveAll(list);}
    public void update(Film object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(Film object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}
}
