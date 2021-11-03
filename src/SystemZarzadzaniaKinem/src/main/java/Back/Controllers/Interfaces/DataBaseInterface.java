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

    public void saveAllFilm();
    public void saveAllCinema();
    public void saveAllFilmCategory();
    public void saveAllGiftCard();
    public void saveAllItems();
    public void saveAllPayment();
    public void saveAllReservation();
    public void saveAllRoom();
    public void saveAllSeans();
    public void saveAllSeat();
    public void saveAllTicket();
    public void saveAllUser();

}
