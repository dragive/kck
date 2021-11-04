package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
public class Staff extends User{

    private String role;
    private List<Permission> permissions;
}
