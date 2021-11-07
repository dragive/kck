package Back.Services;

import Back.Models.*;

import java.util.List;

public interface IDataBaseService {
    public abstract List<Film> getAllFilm();
    public abstract List<Cinema> getAllCinema();
    public abstract List<FilmCategory> getAllFilmCategory();
    public abstract List<Payment> getAllPayment();
    public abstract List<Reservation> getAllReservation();
    public abstract List<Room> getAllRoom();
    public abstract List<Seans> getAllSeans();
    public abstract List<Seat> getAllSeat();
    public abstract List<User> getAllUser();

    public abstract void saveAllFilm(List<Film> list);
    public abstract void saveAllCinema(List<Cinema> list);
    public abstract void saveAllFilmCategory(List<FilmCategory> list);
    public abstract void saveAllPayment(List<Payment> list);
    public abstract void saveAllReservation(List<Reservation> list);
    public abstract void saveAllRoom(List<Room> list);
    public abstract void saveAllSeans(List<Seans> list);
    public abstract void saveAllSeat(List<Seat> list);
    public abstract void saveAllUser(List<User> list);

    public abstract <T> String ConvertToFileConvention(T object);
}
