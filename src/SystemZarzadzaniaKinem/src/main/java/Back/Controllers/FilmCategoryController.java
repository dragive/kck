package Back.Controllers;

import Back.Models.Cinema;
import Back.Models.FilmCategory;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class FilmCategoryController {

    public List<FilmCategory> getAll(){
        return FileDataBaseService.getInstance().getAllFilmCategory();
    }
    public FilmCategory getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}

    public void createNew(FilmCategory filmCategory){}
    public void update(FilmCategory filmCategory){}
    public void delete(FilmCategory filmCategory){}

}
