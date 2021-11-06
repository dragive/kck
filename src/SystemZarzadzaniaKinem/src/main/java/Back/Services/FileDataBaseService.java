package Back.Services;


import Back.Models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileDataBaseService extends DataBaseService {

    private static FileDataBaseService instance;
    private static String DIRECTORY = "Data/";
    private static String FILM_FILE = "film.json";
    private static String CINEMA_FILE = "cinema.json";
    private static String CITY_FILE = "city.json";
    private static String FILM_CATEGORY_FILE = "film_category.json";
    private static String GIFT_CARD_FILE = "gift_card.json";
    private static String ITEMS_FILE = "items.json";
    private static String PAYMENT_FILE = "payment.json";
    private static String PERMISSION_FILE = "permission.json";
    private static String RESERVATION_FILE = "reservation.json";
    private static String ROOM_FILE = "room.json";
    private static String SEANS_FILE = "seans.json";
    private static String SEAT_FILE = "seat.json";
    private static String STAFF_FILE = "staff.json";
    private static String TICKET_FILE = "ticket.json";
    private static String TICKET_TYPE_FILE = "ticket_type.json";
    private static String USER_FILE = "user.json";

    private FileDataBaseService() {};

    public static FileDataBaseService getInstance() {
        if(instance == null){
            instance = new FileDataBaseService();
        } return instance;
    }

    public <T> String ConvertToFileConvention(T object){
        String className = object.getClass().getSimpleName();
        String[] splittedClassName = className.split("(?=\\p{Upper})");
        splittedClassName = Arrays.stream(splittedClassName).map(name -> name.toUpperCase()).toArray(size -> new String[size]);
        StringBuilder result = (DIRECTORY==null)?new StringBuilder():new StringBuilder(DIRECTORY);
        for(String name : splittedClassName) {
            result.append(name+"_");
        }
        result.append("FILE");
        return result.toString();
    }



    @SneakyThrows
    public List<FilmCategory> getAllFilmCategory() {
        List<FilmCategory> ret ;
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(FILM_CATEGORY_FILE);
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(FilmCategory.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllFilmCategory(List<FilmCategory> list) {
        File file = new File(FILM_CATEGORY_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Cinema> getAllCinema() {
        List<Cinema> ret;

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(CINEMA_FILE);
        if(file.exists()) ret = objectMapper.readerForListOf(Cinema.class).readValue(file);
        else ret = new ArrayList<>();
        return ret;
    }

    @SneakyThrows
    public void saveAllCinema(List<Cinema> list) {

        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(CINEMA_FILE);
        if(!file.exists()) file.createNewFile();
        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<City> getAllCity() {
        List<City> ret ;
        File file = new File(CITY_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(City.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllCity(List<City> list) {
        File file = new File(CITY_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Film> getAllFilm() {
        List<Film> ret ;
        File file = new File(FILM_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Film.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllFilm(List<Film> list) {
        File file = new File(FILM_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<GiftCard> getAllGiftCard() {
        List<GiftCard> ret ;
        File file = new File(GIFT_CARD_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(GiftCard.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllGiftCard(List<GiftCard> list) {
        File file = new File(GIFT_CARD_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Items> getAllItems() {
        List<Items> ret ;
        File file = new File(ITEMS_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Items.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllItems(List<Items> list) {
        File file = new File(ITEMS_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Payment> getAllPayment() {
        List<Payment> ret ;
        File file = new File(PAYMENT_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Payment.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllPayment(List<Payment> list) {
        File file = new File(PAYMENT_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Permission> getAllPermission() {
        List<Permission> ret ;
        File file = new File(PERMISSION_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Permission.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllPermission(List<Permission> list) {
        File file = new File(PERMISSION_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(PERMISSION_FILE),list);

    }
    @SneakyThrows
    public List<Reservation> getAllReservation() {
        List<Reservation> ret ;
        File file = new File(RESERVATION_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Reservation.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllReservation(List<Reservation> list) {
        File file = new File(RESERVATION_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Room> getAllRoom() {
        List<Room> ret ;
        File file = new File(ROOM_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Room.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllRoom(List<Room> list) {
        File file = new File(ROOM_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Seans> getAllSeans() {
        List<Seans> ret ;
        File file = new File(SEANS_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Seans.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllSeans(List<Seans> list) {
        File file = new File(SEANS_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Seat> getAllSeat() {
        List<Seat> ret ;
        File file = new File(SEAT_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Seat.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllSeat(List<Seat> list) {
        File file = new File(SEAT_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Staff> getAllStaff() {
        List<Staff> ret ;
        File file = new File(STAFF_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Staff.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllStaff(List<Staff> list) {
        File file = new File(STAFF_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<TicketType> getAllTicketType() {
        List<TicketType> ret ;
        File file = new File(TICKET_TYPE_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(TicketType.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllTicketType(List<TicketType> list) {
        File file = new File(TICKET_TYPE_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<Ticket> getAllTicket() {
        List<Ticket> ret ;
        File file = new File(TICKET_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(Ticket.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllTicket(List<Ticket> list) {
        File file = new File(TICKET_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }
    @SneakyThrows
    public List<User> getAllUser() {
        List<User> ret ;
        File file = new File(USER_FILE);
        ObjectMapper objectMapper = new ObjectMapper();
        if(!file.exists()) {
            ret = new ArrayList<>();
        }
        else ret = objectMapper.readerForListOf(User.class).readValue(file);

        return ret;
    }

    @SneakyThrows
    public void saveAllUser(List<User> list) {
        File file = new File(USER_FILE);
        if(!file.exists()) file.createNewFile();
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(file,list);

    }

}
