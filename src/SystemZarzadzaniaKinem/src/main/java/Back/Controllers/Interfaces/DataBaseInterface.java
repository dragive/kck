package Back.Controllers.Interfaces;

import Back.Models.*;

import java.util.List;

public interface DataBaseInterface {
    public List<Film> getAllFilm();
    public List<Cinema> getAllCinema();
    public List<FilmCategory> getAllFilmCategory();
    public List<GiftCard> getAllGiftCard();
    public List<Items> getAllItems();
    public List<Payment> getAllPayment();
    public List<Reservation> getAllReservation();
    public List<Room> getAllRoom();
    public List<Seans> getAllSeans();
    public List<Seat> getAllSeat();
    public List<Ticket> getAllTicket();
    public List<User> getAllUser();
    public List<City> getAllCity();

    public void saveAllFilm(List<Film> list);
    public void saveAllCinema(List<Cinema> list);
    public void saveAllFilmCategory(List<FilmCategory> list);
    public void saveAllGiftCard(List<GiftCard> list);
    public void saveAllItems(List<Items> list);
    public void saveAllPayment(List<Payment> list);
    public void saveAllReservation(List<Reservation> list);
    public void saveAllRoom(List<Room> list);
    public void saveAllSeans(List<Seans> list);
    public void saveAllSeat(List<Seat> list);
    public void saveAllTicket(List<Ticket> list);
    public void saveAllUser(List<User> list);
    public void saveAllCity(List<City> list);

}
