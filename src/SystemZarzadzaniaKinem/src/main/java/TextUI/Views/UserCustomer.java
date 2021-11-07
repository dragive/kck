package TextUI.Views;

public abstract class UserCustomer {

    private AddUserView addUserView;
    private EditUserView editUserView;
    private FilmCategoryView filmCategoryView;
    private FilmView filmView;
    private LoginView loginView;
    private MenuView menuView;
    private ReservationSeansView reservationSeansView;
    private RoomView roomView;
    private SeansView seansView;
    private UserView userView;

    public UserCustomer(AddUserView addUserView) {
        this.addUserView = addUserView;
    }

    public UserCustomer(EditUserView editUserView) {
        this.editUserView = editUserView;
    }

    public UserCustomer(FilmCategoryView filmCategoryView) {
        this.filmCategoryView = filmCategoryView;
    }

    public UserCustomer(FilmView filmView) {
        this.filmView = filmView;
    }

    public UserCustomer(LoginView loginView) {
        this.loginView = loginView;
    }

    public UserCustomer(MenuView menuView) {
        this.menuView = menuView;
    }

    public UserCustomer(ReservationSeansView reservationSeansView) {
        this.reservationSeansView = reservationSeansView;
    }

    public UserCustomer(RoomView roomView) {
        this.roomView = roomView;
    }

    public UserCustomer(SeansView seansView) {
        this.seansView = seansView;
    }

    public UserCustomer(UserView userView) {
        this.userView = userView;
    }

    public AddUserView getAddUserView() {
        return addUserView;
    }

    public void setAddUserView(AddUserView addUserView) {
        this.addUserView = addUserView;
    }

    public EditUserView getEditUserView() {
        return editUserView;
    }

    public void setEditUserView(EditUserView editUserView) {
        this.editUserView = editUserView;
    }

    public FilmCategoryView getFilmCategoryView() {
        return filmCategoryView;
    }

    public void setFilmCategoryView(FilmCategoryView filmCategoryView) {
        this.filmCategoryView = filmCategoryView;
    }

    public FilmView getFilmView() {
        return filmView;
    }

    public void setFilmView(FilmView filmView) {
        this.filmView = filmView;
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }

    public ReservationSeansView getReservationSeansView() {
        return reservationSeansView;
    }

    public void setReservationSeansView(ReservationSeansView reservationSeansView) {
        this.reservationSeansView = reservationSeansView;
    }

    public RoomView getRoomView() {
        return roomView;
    }

    public void setRoomView(RoomView roomView) {
        this.roomView = roomView;
    }

    public SeansView getSeansView() {
        return seansView;
    }

    public void setSeansView(SeansView seansView) {
        this.seansView = seansView;
    }

    public UserView getUserView() {
        return userView;
    }

    public void setUserView(UserView userView) {
        this.userView = userView;
    }

}
