package TextUI.Views;

public abstract class Manager extends Staff{

    private AddCinemaView addCinemaView;
    private CinemaListView cinemaListView;
    private CityListView cityListView;
    private AddFilmCategoryView addFilmCategoryView;
    private AddFilmView addFilmView;
    private AddRoomView addRoomView;
    private AddSeansView addSeansView;
    private FilmCategoryListView filmCategoryListView;
    private ReservationCinemaListView reservationCinemaListView;
    private ReservationSeansListView reservationSeansListView;
    private RoomListView roomListView;
    private SeansAddFilmView seansAddFilmView;
    private SeansListView seansListView;

    public Manager(AddUserView addUserView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(addUserView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(EditUserView editUserView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(editUserView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(FilmCategoryView filmCategoryView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(filmCategoryView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(FilmView filmView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(filmView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(LoginView loginView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(loginView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(MenuView menuView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(menuView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(ReservationSeansView reservationSeansView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(reservationSeansView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(RoomView roomView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(roomView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(SeansView seansView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(seansView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public Manager(UserView userView, CinemaView cinemaView, AddCinemaView addCinemaView, CinemaListView cinemaListView, CityListView cityListView, AddFilmCategoryView addFilmCategoryView, AddFilmView addFilmView, AddRoomView addRoomView, AddSeansView addSeansView, FilmCategoryListView filmCategoryListView, ReservationCinemaListView reservationCinemaListView, ReservationSeansListView reservationSeansListView, RoomListView roomListView, SeansAddFilmView seansAddFilmView, SeansListView seansListView, UserListView userListView) {
        super(userView, cinemaView);
        this.addCinemaView = addCinemaView;
        this.cinemaListView = cinemaListView;
        this.cityListView = cityListView;
        this.addFilmCategoryView = addFilmCategoryView;
        this.addFilmView = addFilmView;
        this.addRoomView = addRoomView;
        this.addSeansView = addSeansView;
        this.filmCategoryListView = filmCategoryListView;
        this.reservationCinemaListView = reservationCinemaListView;
        this.reservationSeansListView = reservationSeansListView;
        this.roomListView = roomListView;
        this.seansAddFilmView = seansAddFilmView;
        this.seansListView = seansListView;
        this.userListView = userListView;
    }

    public AddCinemaView getAddCinemaView() {
        return addCinemaView;
    }

    public void setAddCinemaView(AddCinemaView addCinemaView) {
        this.addCinemaView = addCinemaView;
    }

    public CinemaListView getCinemaListView() {
        return cinemaListView;
    }

    public void setCinemaListView(CinemaListView cinemaListView) {
        this.cinemaListView = cinemaListView;
    }

    public CityListView getCityListView() {
        return cityListView;
    }

    public void setCityListView(CityListView cityListView) {
        this.cityListView = cityListView;
    }

    public AddFilmCategoryView getAddFilmCategoryView() {
        return addFilmCategoryView;
    }

    public void setAddFilmCategoryView(AddFilmCategoryView addFilmCategoryView) {
        this.addFilmCategoryView = addFilmCategoryView;
    }

    public AddFilmView getAddFilmView() {
        return addFilmView;
    }

    public void setAddFilmView(AddFilmView addFilmView) {
        this.addFilmView = addFilmView;
    }

    public AddRoomView getAddRoomView() {
        return addRoomView;
    }

    public void setAddRoomView(AddRoomView addRoomView) {
        this.addRoomView = addRoomView;
    }

    public AddSeansView getAddSeansView() {
        return addSeansView;
    }

    public void setAddSeansView(AddSeansView addSeansView) {
        this.addSeansView = addSeansView;
    }

    public FilmCategoryListView getFilmCategoryListView() {
        return filmCategoryListView;
    }

    public void setFilmCategoryListView(FilmCategoryListView filmCategoryListView) {
        this.filmCategoryListView = filmCategoryListView;
    }

    public ReservationCinemaListView getReservationCinemaListView() {
        return reservationCinemaListView;
    }

    public void setReservationCinemaListView(ReservationCinemaListView reservationCinemaListView) {
        this.reservationCinemaListView = reservationCinemaListView;
    }

    public ReservationSeansListView getReservationSeansListView() {
        return reservationSeansListView;
    }

    public void setReservationSeansListView(ReservationSeansListView reservationSeansListView) {
        this.reservationSeansListView = reservationSeansListView;
    }

    public RoomListView getRoomListView() {
        return roomListView;
    }

    public void setRoomListView(RoomListView roomListView) {
        this.roomListView = roomListView;
    }

    public SeansAddFilmView getSeansAddFilmView() {
        return seansAddFilmView;
    }

    public void setSeansAddFilmView(SeansAddFilmView seansAddFilmView) {
        this.seansAddFilmView = seansAddFilmView;
    }

    public SeansListView getSeansListView() {
        return seansListView;
    }

    public void setSeansListView(SeansListView seansListView) {
        this.seansListView = seansListView;
    }

    public UserListView getUserListView() {
        return userListView;
    }

    public void setUserListView(UserListView userListView) {
        this.userListView = userListView;
    }

    private UserListView userListView;


}
