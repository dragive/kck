package Back.Controllers.Interfaces;

import Back.Models.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileJSONDatabase implements DataBaseInterface{

    @SneakyThrows
    public List<Film> getAllFilm() {
        List<Film> ret = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.readerForListOf(Film.class).readValue(new File("test.json"));

        return ret;
    }
    @SneakyThrows
    public List<Film> getAllFilmTest(){
        List<Film> ret = new ArrayList<>();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.readerForListOf(Film.class).readValue("[]");

        return ret;
    }

    public List<Cinema> getAllCinema() {
        return null;
    }

    public List<FilmCategory> getAllFilmCategory() {
        return null;
    }

    public List<GiftCard> getAllGiftCard() {
        return null;
    }

    public List<Items> getAllItems() {
        return null;
    }

    public List<Payment> getAllPayment() {
        return null;
    }

    public List<Reservation> getAllReservation() {
        return null;
    }

    public List<Room> getAllRoom() {
        return null;
    }

    public List<Seans> getAllSeans() {
        return null;
    }

    public List<Seat> getAllSeat() {
        return null;
    }

    public List<Ticket> getAllTicket() {
        return null;
    }

    public List<User> getAllUser() {
        return null;
    }

    public void saveAllFilm() {

    }

    public void saveAllCinema() {

    }

    public void saveAllFilmCategory() {

    }

    public void saveAllGiftCard() {

    }

    public void saveAllItems() {

    }

    public void saveAllPayment() {

    }

    public void saveAllReservation() {

    }

    public void saveAllRoom() {

    }

    public void saveAllSeans() {

    }

    public void saveAllSeat() {

    }

    public void saveAllTicket() {

    }

    public void saveAllUser() {

    }
}
