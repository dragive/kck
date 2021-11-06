package Back.Controllers;

import Back.Models.Cinema;
import Back.Models.Film;
import Back.Services.DataBaseService;
import Back.Models.FilmCategory;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

public class FilmCategoryController {

    public List<FilmCategory> getAll(){
        return DataBaseService.getInstance().getAllFilmCategory();
    }
    public void saveAll(List<FilmCategory> list){ DataBaseService.getInstance().saveAllFilmCategory(list);}
    public FilmCategory getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Film> getCategoryFilms(Integer id){FilmCategory filmCategory = this.getById(id);return filmCategory.getFilmList();}

    public void createNew(FilmCategory object){List list = this.getAll();if(list.size()==0) object.setId(1);else {FilmCategory last = (FilmCategory)list.get(list.size()-1);object.setId(last.getId()+1);}list.add(object);this.saveAll(list);}
    public void update(FilmCategory object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(FilmCategory object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}

}
