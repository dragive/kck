package Back.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class Staff extends User{

    private String role;
    private List<Permission> permissions;
}
