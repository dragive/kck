package Back.Models;

import lombok.Data;

import java.util.List;


@Data
public class City {
    private Integer id;
    private String name;
    private List<Cinema> cinemaList;
}
