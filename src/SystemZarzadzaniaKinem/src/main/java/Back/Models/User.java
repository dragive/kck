package Back.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class User {
    private Integer id;
    private String email;
    private String passwordHash;
    private String name;
    private Date registrationDate;
}
