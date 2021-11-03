package Back.Models;

public enum Permission {
    KIEROWNIK(10,"Kierownik"),SPRZEDARZ_BILETOW(1,"Sprzedawca biletów"),BASIC(0,"Podstawowy");
    private Integer id;
    private String role;

    Permission(Integer id, String role){
        this.id = id;
        this.role = role;
    }

}
