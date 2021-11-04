import Back.Models.Film;
import Back.Services.FileDataBaseService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Main {
    public static void main(String[] args){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Film> list = new ArrayList<>();
//        list.add(Film.builder().id(1).filmCategoryId(null).description("123").releaseDate(Calendar.getInstance().getTime()).build());
        list.add(new Film());
        fileDataBaseService.saveAllFilm(list);


        List<Film> out = fileDataBaseService.getAllFilm();
        System.out.println(list);
        System.out.println(out);
    }
}
