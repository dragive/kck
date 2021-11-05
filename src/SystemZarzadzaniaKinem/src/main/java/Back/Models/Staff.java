package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Data
public class Staff {
    private Integer id;
    private String email;
    private String passwordHash;
    private String name;
    private Date registrationDate;


    private String role;
    private List<Permission> permissions;


}
