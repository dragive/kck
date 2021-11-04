package Back.Services;

import Back.Controllers.FilmController;
import Back.Controllers.Interfaces.DataBaseInterface;
import Back.Models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileDataBaseService implements DataBaseInterface {

    private static FileDataBaseService instance;
    public static FileDataBaseService getInstance() {if(instance == null){instance = new FileDataBaseService();} return instance;}
    private FileDataBaseService(){};

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


    @SneakyThrows
    public List<FilmCategory> getAllFilmCategory() {
        List<FilmCategory> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(FilmCategory.class).readValue(new File(FILM_CATEGORY_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllFilmCategory(List<FilmCategory> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(FILM_CATEGORY_FILE),list);

    }
    @SneakyThrows
    public List<Cinema> getAllCinema() {
        List<Cinema> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Cinema.class).readValue(new File(CINEMA_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllCinema(List<Cinema> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(CINEMA_FILE),list);

    }
    @SneakyThrows
    public List<City> getAllCity() {
        List<City> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(City.class).readValue(new File(CITY_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllCity(List<City> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(CITY_FILE),list);

    }
    @SneakyThrows
    public List<Film> getAllFilm() {
        List<Film> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Film.class).readValue(new File(FILM_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllFilm(List<Film> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(FILM_FILE),list);

    }
    @SneakyThrows
    public List<GiftCard> getAllGiftCard() {
        List<GiftCard> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(GiftCard.class).readValue(new File(GIFT_CARD_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllGiftCard(List<GiftCard> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(GIFT_CARD_FILE),list);

    }
    @SneakyThrows
    public List<Items> getAllItems() {
        List<Items> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Items.class).readValue(new File(ITEMS_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllItems(List<Items> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(ITEMS_FILE),list);

    }
    @SneakyThrows
    public List<Payment> getAllPayment() {
        List<Payment> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Payment.class).readValue(new File(PAYMENT_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllPayment(List<Payment> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(PAYMENT_FILE),list);

    }
    @SneakyThrows
    public List<Permission> getAllPermission() {
        List<Permission> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Permission.class).readValue(new File(PERMISSION_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllPermission(List<Permission> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(PERMISSION_FILE),list);

    }
    @SneakyThrows
    public List<Reservation> getAllReservation() {
        List<Reservation> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Reservation.class).readValue(new File(RESERVATION_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllReservation(List<Reservation> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(RESERVATION_FILE),list);

    }
    @SneakyThrows
    public List<Room> getAllRoom() {
        List<Room> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Room.class).readValue(new File(ROOM_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllRoom(List<Room> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(ROOM_FILE),list);

    }
    @SneakyThrows
    public List<Seans> getAllSeans() {
        List<Seans> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Seans.class).readValue(new File(SEANS_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllSeans(List<Seans> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(SEANS_FILE),list);

    }
    @SneakyThrows
    public List<Seat> getAllSeat() {
        List<Seat> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Seat.class).readValue(new File(SEAT_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllSeat(List<Seat> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(SEAT_FILE),list);

    }
    @SneakyThrows
    public List<Staff> getAllStaff() {
        List<Staff> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Staff.class).readValue(new File(STAFF_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllStaff(List<Staff> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(STAFF_FILE),list);

    }
    @SneakyThrows
    public List<TicketType> getAllTicketType() {
        List<TicketType> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(TicketType.class).readValue(new File(TICKET_TYPE_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllTicketType(List<TicketType> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(TICKET_TYPE_FILE),list);

    }
    @SneakyThrows
    public List<Ticket> getAllTicket() {
        List<Ticket> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(Ticket.class).readValue(new File(TICKET_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllTicket(List<Ticket> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(TICKET_FILE),list);

    }
    @SneakyThrows
    public List<User> getAllUser() {
        List<User> ret ;

        ObjectMapper objectMapper = new ObjectMapper();

        ret = objectMapper.readerForListOf(User.class).readValue(new File(USER_FILE));

        return ret;
    }

    @SneakyThrows
    public void saveAllUser(List<User> list) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File(USER_FILE),list);

    }

}
