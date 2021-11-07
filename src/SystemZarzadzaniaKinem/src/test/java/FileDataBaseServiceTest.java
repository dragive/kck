import Back.Services.DataBaseService;
import Back.Models.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileDataBaseServiceTest {

    @Test
    public void Class_name_convert_to_file_name_convention(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();
        FilmCategory filmCategory = new FilmCategory();
        String className = fileDataBaseService.ConvertToFileConvention(filmCategory);
        System.out.println(className);
        Assert.assertEquals("Data/FILM_CATEGORY_FILE",className);
    }
    
    @Test
    public void Cinema(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<Cinema> list = new ArrayList<>();
        list.add(new Cinema());
        fileDataBaseService.saveAllCinema(list);


        List<Cinema> out = fileDataBaseService.getAllCinema();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Film(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<Film> list = new ArrayList<>();
        list.add(new Film());
        fileDataBaseService.saveAllFilm(list);


        List<Film> out = fileDataBaseService.getAllFilm();
        Assert.assertTrue(out.equals(list));

    }

    @Test
    public void Reservation(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<Reservation> list = new ArrayList<>();
        list.add(new Reservation());
        fileDataBaseService.saveAllReservation(list);


        List<Reservation> out = fileDataBaseService.getAllReservation();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Room(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<Room> list = new ArrayList<>();
        list.add(new Room());
        fileDataBaseService.saveAllRoom(list);


        List<Room> out = fileDataBaseService.getAllRoom();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Seans(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<Seans> list = new ArrayList<>();
        list.add(new Seans());
        fileDataBaseService.saveAllSeans(list);


        List<Seans> out = fileDataBaseService.getAllSeans();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Seat(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<Seat> list = new ArrayList<>();
        list.add(new Seat());
        fileDataBaseService.saveAllSeat(list);


        List<Seat> out = fileDataBaseService.getAllSeat();
        Assert.assertTrue(out.equals(list));

    }

    @Test
    public void Payment(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<Payment> list = new ArrayList<>();
        list.add(new Payment());
        fileDataBaseService.saveAllPayment(list);


        List<Payment> out = fileDataBaseService.getAllPayment();
        Assert.assertTrue(out.equals(list));

    }

    @Test
    public void User(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<User> list = new ArrayList<>();
        list.add(new User());
        fileDataBaseService.saveAllUser(list);


        List<User> out = fileDataBaseService.getAllUser();
        Assert.assertTrue(out.equals(list));

    }

    @Test
    public void Permission(){
        DataBaseService fileDataBaseService = DataBaseService.getInstance();

        List<User> list = new ArrayList<>();
        list.add(new User());
        fileDataBaseService.saveAllUser(list);


        List<User> out = fileDataBaseService.getAllUser();
        Assert.assertTrue(out.equals(list));

    }

}
