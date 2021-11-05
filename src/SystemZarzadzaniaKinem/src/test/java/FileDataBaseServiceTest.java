import Back.Controllers.PaymentController;
import Back.Models.*;
import Back.Services.FileDataBaseService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class FileDataBaseServiceTest {

    @Test
    public void Class_name_convert_to_file_name_convention(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();
        FilmCategory filmCategory = new FilmCategory();
        String className = fileDataBaseService.ConvertToFileConvention(filmCategory);
        System.out.println(className);
        Assert.assertEquals("Data/FILM_CATEGORY_FILE",className);
    }
    
    @Test
    public void Cinema_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Cinema> list = new ArrayList<>();
        list.add(new Cinema());
        fileDataBaseService.saveAllCinema(list);


        List<Cinema> out = fileDataBaseService.getAllCinema();
        Assert.assertTrue(out.equals(list));

    }
    
    @Test
    public void City_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<City> list = new ArrayList<>();
        list.add(new City());
        fileDataBaseService.saveAllCity(list);


        List<City> out = fileDataBaseService.getAllCity();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Film_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Film> list = new ArrayList<>();
        list.add(new Film());
        fileDataBaseService.saveAllFilm(list);


        List<Film> out = fileDataBaseService.getAllFilm();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Gift_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<GiftCard> list = new ArrayList<>();
        list.add(new GiftCard());
        fileDataBaseService.saveAllGiftCard(list);


        List<GiftCard> out = fileDataBaseService.getAllGiftCard();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Items_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Items> list = new ArrayList<>();
        list.add(new Items());
        fileDataBaseService.saveAllItems(list);


        List<Items> out = fileDataBaseService.getAllItems();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Reservation_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Reservation> list = new ArrayList<>();
        list.add(new Reservation());
        fileDataBaseService.saveAllReservation(list);


        List<Reservation> out = fileDataBaseService.getAllReservation();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Room_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Room> list = new ArrayList<>();
        list.add(new Room());
        fileDataBaseService.saveAllRoom(list);


        List<Room> out = fileDataBaseService.getAllRoom();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Seans_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Seans> list = new ArrayList<>();
        list.add(new Seans());
        fileDataBaseService.saveAllSeans(list);


        List<Seans> out = fileDataBaseService.getAllSeans();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Seat_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Seat> list = new ArrayList<>();
        list.add(new Seat());
        fileDataBaseService.saveAllSeat(list);


        List<Seat> out = fileDataBaseService.getAllSeat();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void Staff_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Staff> list = new ArrayList<>();
        list.add(new Staff());
        fileDataBaseService.saveAllStaff(list);


        List<Staff> out = fileDataBaseService.getAllStaff();
        Assert.assertTrue(out.equals(list));

    }



    @Test
    public void Ticket_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<Ticket> list = new ArrayList<>();
        list.add(new Ticket());
        fileDataBaseService.saveAllTicket(list);


        List<Ticket> out = fileDataBaseService.getAllTicket();
        Assert.assertTrue(out.equals(list));

    }


    @Test
    public void User_serialization(){
        FileDataBaseService fileDataBaseService = FileDataBaseService.getInstance();

        List<User> list = new ArrayList<>();
        list.add(new User());
        fileDataBaseService.saveAllUser(list);


        List<User> out = fileDataBaseService.getAllUser();
        Assert.assertTrue(out.equals(list));

    }

}
