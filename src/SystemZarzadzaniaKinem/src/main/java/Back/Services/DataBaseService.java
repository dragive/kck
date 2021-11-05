package Back.Services;

import Back.Models.*;

import java.util.List;

public abstract class DataBaseService {
    private static DataBaseService instance;
    public static DataBaseService getInstance(){
        if(instance == null){
            instance = FileDataBaseService.getInstance();
        }
        return instance;
    }

    public abstract List<Film> getAllFilm();
    public abstract List<Cinema> getAllCinema();
    public abstract List<FilmCategory> getAllFilmCategory();
    public abstract List<GiftCard> getAllGiftCard();
    public abstract List<Items> getAllItems();
    public abstract List<Payment> getAllPayment();
    public abstract List<Reservation> getAllReservation();
    public abstract List<Room> getAllRoom();
    public abstract List<Seans> getAllSeans();
    public abstract List<Seat> getAllSeat();
    public abstract List<Ticket> getAllTicket();
    public abstract List<TicketType> getAllTicketType();
    public abstract List<User> getAllUser();
    public abstract List<City> getAllCity();
    public abstract List<Permission> getAllPermission();
    public abstract List<Staff> getAllStaff();

    public abstract void saveAllFilm(List<Film> list);
    public abstract void saveAllCinema(List<Cinema> list);
    public abstract void saveAllFilmCategory(List<FilmCategory> list);
    public abstract void saveAllGiftCard(List<GiftCard> list);
    public abstract void saveAllItems(List<Items> list);
    public abstract void saveAllPayment(List<Payment> list);
    public abstract void saveAllReservation(List<Reservation> list);
    public abstract void saveAllRoom(List<Room> list);
    public abstract void saveAllSeans(List<Seans> list);
    public abstract void saveAllSeat(List<Seat> list);
    public abstract void saveAllTicket(List<Ticket> list);
    public abstract void saveAllTicketType(List<TicketType> list);
    public abstract void saveAllUser(List<User> list);
    public abstract void saveAllCity(List<City> list);
    public abstract void saveAllPermission(List<Permission> list);
    public abstract void saveAllStaff(List<Staff> list);

}
