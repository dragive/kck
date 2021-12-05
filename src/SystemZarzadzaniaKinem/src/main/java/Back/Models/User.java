package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String email;
    private String passwordHash;
    private String name;
    private Date registrationDate;
    private boolean permission;
}
