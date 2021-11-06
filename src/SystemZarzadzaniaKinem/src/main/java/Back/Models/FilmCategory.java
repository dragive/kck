package Back.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class FilmCategory {
    private Integer id;
    private String name;
    private List<Film> filmList;
}
