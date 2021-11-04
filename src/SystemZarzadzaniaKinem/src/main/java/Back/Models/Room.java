package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Data
public class Room  {
    private Integer id;
    private String name;
    private Integer cinemaId;
}
