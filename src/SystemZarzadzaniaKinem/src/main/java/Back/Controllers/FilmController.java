package Back.Controllers;

import Back.Models.Film;
import Back.Models.FilmCategory;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.List;

public class FilmController {
    public Film getById(Integer id){return this.getAll().stream().filter(o->{return o.getId().equals(id);}).findFirst().get();}
    public List<Film> getAll(){
        return FileDataBaseService.getInstance().getAllFilm();
    }

    public void createNew(Film film){}
    public void update(Film film){}
    public void delete(Film film){}
}
