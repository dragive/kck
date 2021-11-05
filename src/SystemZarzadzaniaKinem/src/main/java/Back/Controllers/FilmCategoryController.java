package Back.Controllers;

import Back.Services.DataBaseService;
import Back.Models.FilmCategory;

import java.util.List;
import java.util.stream.Collectors;

public class FilmCategoryController {

    public List<FilmCategory> getAll(){
        return DataBaseService.getInstance().getAllFilmCategory();
    }
    public void saveAll(List<FilmCategory> list){ DataBaseService.getInstance().saveAllFilmCategory(list);}
    public FilmCategory getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}

    public void createNew(FilmCategory object){List list = this.getAll();list.add(object);this.saveAll(list);}
    public void update(FilmCategory object){this.saveAll(this.getAll().stream().map((o)->{if(o.getId() == object.getId())return object; else return o;}).collect(Collectors.toList()));}
    public void delete(FilmCategory object){this.saveAll(this.getAll().stream().filter((o)->{return !o.equals(object);}).collect(Collectors.toList()));}

}
