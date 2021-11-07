package TextUI.Views;

public abstract class Staff extends UserCustomer{

    private CinemaView cinemaView;

    public Staff(AddUserView addUserView, CinemaView cinemaView) {
        super(addUserView);
        this.cinemaView = cinemaView;
    }

    public Staff(EditUserView editUserView, CinemaView cinemaView) {
        super(editUserView);
        this.cinemaView = cinemaView;
    }

    public Staff(FilmCategoryView filmCategoryView, CinemaView cinemaView) {
        super(filmCategoryView);
        this.cinemaView = cinemaView;
    }

    public Staff(FilmView filmView, CinemaView cinemaView) {
        super(filmView);
        this.cinemaView = cinemaView;
    }

    public Staff(LoginView loginView, CinemaView cinemaView) {
        super(loginView);
        this.cinemaView = cinemaView;
    }

    public Staff(MenuView menuView, CinemaView cinemaView) {
        super(menuView);
        this.cinemaView = cinemaView;
    }

    public Staff(ReservationSeansView reservationSeansView, CinemaView cinemaView) {
        super(reservationSeansView);
        this.cinemaView = cinemaView;
    }

    public Staff(RoomView roomView, CinemaView cinemaView) {
        super(roomView);
        this.cinemaView = cinemaView;
    }

    public Staff(SeansView seansView, CinemaView cinemaView) {
        super(seansView);
        this.cinemaView = cinemaView;
    }

    public Staff(UserView userView, CinemaView cinemaView) {
        super(userView);
        this.cinemaView = cinemaView;
    }

    public CinemaView getCinemaView() {
        return cinemaView;
    }

    public void setCinemaView(CinemaView cinemaView) {
        this.cinemaView = cinemaView;
    }
}
