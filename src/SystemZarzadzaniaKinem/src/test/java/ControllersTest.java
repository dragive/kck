import Back.Controllers.*;
import Back.Models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ControllersTest {

    private final static Integer NO_OF_OBJECTS = 5;

    /// BEGIN /// FILMCATEGORY ///
    @Test
    public void FilmCategoryBasicOperationsWriteAndReadTest(){
        List<FilmCategory> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            FilmCategory object = new FilmCategory();
            lista.add(object);
        }
        FilmCategoryController controller = new FilmCategoryController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void FilmCategoryBasicOperationsWriteUpdateReadTest(){
        List<FilmCategory> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            FilmCategory object = new FilmCategory();
            object.setId(i+1);
            lista.add(object);
        }
        FilmCategoryController controller = new FilmCategoryController();
        FilmCategory updated = new FilmCategory();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<FilmCategory> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (FilmCategory object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void FilmCategoryBasicOperationsWriteDeleteReadTest(){
        List<FilmCategory> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            FilmCategory object = new FilmCategory();
            object.setId(i+1);
            lista.add(object);
        }
        FilmCategoryController controller = new FilmCategoryController();
        FilmCategory deleted = new FilmCategory();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<FilmCategory> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (FilmCategory object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void FilmCategoryBasicOperationsWriteCreateReadTest(){
        List<FilmCategory> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            FilmCategory object = new FilmCategory();
            object.setId(i+1);
            lista.add(object);
        }
        FilmCategoryController controller = new FilmCategoryController();
        FilmCategory newObject = new FilmCategory();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<FilmCategory> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (FilmCategory object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (FilmCategory object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// FILMCATEGORY ///

    /// BEGIN /// CINEMA ///
    @Test
    public void CinemaBasicOperationsWriteAndReadTest(){
        List<Cinema> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Cinema object = new Cinema();
            lista.add(object);
        }
        CinemaController controller = new CinemaController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void CinemaBasicOperationsWriteUpdateReadTest(){
        List<Cinema> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Cinema object = new Cinema();
            object.setId(i+1);
            lista.add(object);
        }
        CinemaController controller = new CinemaController();
        Cinema updated = new Cinema();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<Cinema> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (Cinema object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void CinemaBasicOperationsWriteDeleteReadTest(){
        List<Cinema> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Cinema object = new Cinema();
            object.setId(i+1);
            lista.add(object);
        }
        CinemaController controller = new CinemaController();
        Cinema deleted = new Cinema();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<Cinema> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (Cinema object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void CinemaBasicOperationsWriteCreateReadTest(){
        List<Cinema> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Cinema object = new Cinema();
            object.setId(i+1);
            lista.add(object);
        }
        CinemaController controller = new CinemaController();
        Cinema newObject = new Cinema();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<Cinema> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (Cinema object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (Cinema object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// CINEMA ///

    /// BEGIN /// FILM ///
    @Test
    public void FilmBasicOperationsWriteAndReadTest(){
        List<Film> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Film object = new Film();
            lista.add(object);
        }
        FilmController controller = new FilmController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void FilmBasicOperationsWriteUpdateReadTest(){
        List<Film> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Film object = new Film();
            object.setId(i+1);
            lista.add(object);
        }
        FilmController controller = new FilmController();
        Film updated = new Film();
        updated.setId(1);
        updated.setTitle("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<Film> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (Film object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void FilmBasicOperationsWriteDeleteReadTest(){
        List<Film> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Film object = new Film();
            object.setId(i+1);
            lista.add(object);
        }
        FilmController controller = new FilmController();
        Film deleted = new Film();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<Film> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (Film object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void FilmBasicOperationsWriteCreateReadTest(){
        List<Film> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Film object = new Film();
            object.setId(i+1);
            lista.add(object);
        }
        FilmController controller = new FilmController();
        Film newObject = new Film();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<Film> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (Film object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (Film object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// FILM ///


    /// BEGIN /// PAYMENT ///
    @Test
    public void PaymentBasicOperationsWriteAndReadTest(){
        List<Payment> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Payment object = new Payment();
            lista.add(object);
        }
        PaymentController controller = new PaymentController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void PaymentBasicOperationsWriteUpdateReadTest(){
        List<Payment> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Payment object = new Payment();
            object.setId(i+1);
            lista.add(object);
        }
        PaymentController controller = new PaymentController();
        Payment updated = new Payment();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<Payment> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (Payment object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void PaymentBasicOperationsWriteDeleteReadTest(){
        List<Payment> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Payment object = new Payment();
            object.setId(i+1);
            lista.add(object);
        }
        PaymentController controller = new PaymentController();
        Payment deleted = new Payment();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<Payment> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (Payment object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void PaymentBasicOperationsWriteCreateReadTest(){
        List<Payment> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Payment object = new Payment();
            object.setId(i+1);
            lista.add(object);
        }
        PaymentController controller = new PaymentController();
        Payment newObject = new Payment();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<Payment> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (Payment object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (Payment object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// PAYMENT ///

    /// BEGIN /// RESERVATION ///
    @Test
    public void ReservationBasicOperationsWriteAndReadTest(){
        List<Reservation> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Reservation object = new Reservation();
            lista.add(object);
        }
        ReservationController controller = new ReservationController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void ReservationBasicOperationsWriteUpdateReadTest(){
        List<Reservation> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Reservation object = new Reservation();
            object.setId(i+1);
            lista.add(object);
        }
        ReservationController controller = new ReservationController();
        Reservation updated = new Reservation();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<Reservation> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (Reservation object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void ReservationBasicOperationsWriteDeleteReadTest(){
        List<Reservation> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Reservation object = new Reservation();
            object.setId(i+1);
            lista.add(object);
        }
        ReservationController controller = new ReservationController();
        Reservation deleted = new Reservation();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<Reservation> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (Reservation object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void ReservationBasicOperationsWriteCreateReadTest(){
        List<Reservation> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Reservation object = new Reservation();
            object.setId(i+1);
            lista.add(object);
        }
        ReservationController controller = new ReservationController();
        Reservation newObject = new Reservation();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<Reservation> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (Reservation object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (Reservation object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// RESERVATION ///

    /// BEGIN /// ROOM ///
    @Test
    public void RoomBasicOperationsWriteAndReadTest(){
        List<Room> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Room object = new Room();
            lista.add(object);
        }
        RoomsController controller = new RoomsController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void RoomBasicOperationsWriteUpdateReadTest(){
        List<Room> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Room object = new Room();
            object.setId(i+1);
            lista.add(object);
        }
        RoomsController controller = new RoomsController();
        Room updated = new Room();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<Room> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (Room object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void RoomBasicOperationsWriteDeleteReadTest(){
        List<Room> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Room object = new Room();
            object.setId(i+1);
            lista.add(object);
        }
        RoomsController controller = new RoomsController();
        Room deleted = new Room();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<Room> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (Room object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void RoomBasicOperationsWriteCreateReadTest(){
        List<Room> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Room object = new Room();
            object.setId(i+1);
            lista.add(object);
        }
        RoomsController controller = new RoomsController();
        Room newObject = new Room();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<Room> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (Room object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (Room object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// ROOM ///

    /// BEGIN /// SEANS ///
    @Test
    public void SeansBasicOperationsWriteAndReadTest(){
        List<Seans> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seans object = new Seans();
            lista.add(object);
        }
        SeansController controller = new SeansController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void SeansBasicOperationsWriteUpdateReadTest(){
        List<Seans> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seans object = new Seans();
            object.setId(i+1);
            lista.add(object);
        }
        SeansController controller = new SeansController();
        Seans updated = new Seans();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<Seans> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (Seans object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void SeansBasicOperationsWriteDeleteReadTest(){
        List<Seans> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seans object = new Seans();
            object.setId(i+1);
            lista.add(object);
        }
        SeansController controller = new SeansController();
        Seans deleted = new Seans();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<Seans> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (Seans object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void SeansBasicOperationsWriteCreateReadTest(){
        List<Seans> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seans object = new Seans();
            object.setId(i+1);
            lista.add(object);
        }
        SeansController controller = new SeansController();
        Seans newObject = new Seans();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<Seans> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (Seans object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (Seans object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// SEANS ///

    /// BEGIN /// SEAT ///
    @Test
    public void SeatBasicOperationsWriteAndReadTest(){
        List<Seat> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seat object = new Seat();
            lista.add(object);
        }
        SeatController controller = new SeatController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void SeatBasicOperationsWriteUpdateReadTest(){
        List<Seat> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seat object = new Seat();
            object.setId(i+1);
            lista.add(object);
        }
        SeatController controller = new SeatController();
        Seat updated = new Seat();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<Seat> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (Seat object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void SeatBasicOperationsWriteDeleteReadTest(){
        List<Seat> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seat object = new Seat();
            object.setId(i+1);
            lista.add(object);
        }
        SeatController controller = new SeatController();
        Seat deleted = new Seat();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<Seat> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (Seat object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void SeatBasicOperationsWriteCreateReadTest(){
        List<Seat> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            Seat object = new Seat();
            object.setId(i+1);
            lista.add(object);
        }
        SeatController controller = new SeatController();
        Seat newObject = new Seat();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<Seat> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (Seat object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (Seat object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// SEAT ///



    /// BEGIN /// USER ///
    @Test
    public void UserBasicOperationsWriteAndReadTest(){
        List<User> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            User object = new User();
            lista.add(object);
        }
        UsersController controller = new UsersController();
        controller.saveAll(lista);
        List listb = controller.getAll();

        Assert.assertTrue(lista.equals(listb));
    }

    @Test
    public void UserBasicOperationsWriteUpdateReadTest(){
        List<User> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            User object = new User();
            object.setId(i+1);
            lista.add(object);
        }
        UsersController controller = new UsersController();
        User updated = new User();
        updated.setId(1);
        updated.setName("TESTTESTTEST");
        controller.saveAll(lista);
        controller.update(updated);

        List<User> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()==listb.size());
        int countUpdated =0;
        for (User object: listb) {
            if(object.equals(updated))
                countUpdated++;
        }
        Assert.assertTrue(countUpdated==1);
    }

    @Test
    public void UserBasicOperationsWriteDeleteReadTest(){
        List<User> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            User object = new User();
            object.setId(i+1);
            lista.add(object);
        }
        UsersController controller = new UsersController();
        User deleted = new User();
        deleted.setId(1);
        controller.saveAll(lista);
        controller.delete(deleted);

        List<User> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()>listb.size());
        int countDeleted =0;
        for (User object: listb) {
            if(object.equals(deleted))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    @Test
    public void UserBasicOperationsWriteCreateReadTest(){
        List<User> lista = new ArrayList<>();

        for(int i =0; i<NO_OF_OBJECTS;i++){
            User object = new User();
            object.setId(i+1);
            lista.add(object);
        }
        UsersController controller = new UsersController();
        User newObject = new User();
        newObject.setId(NO_OF_OBJECTS+1);
        controller.saveAll(lista);
        controller.createNew(newObject);

        List<User> listb = controller.getAll();

        Assert.assertFalse(lista.equals(listb));
        Assert.assertTrue(lista.size()<listb.size());
        int countDeleted =0;
        for (User object: listb) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==1);
        countDeleted =0;
        for (User object: lista) {
            if(object.equals(newObject))
                countDeleted++;
        }
        Assert.assertTrue(countDeleted==0);
    }

    ///  END  /// USER ///
}
