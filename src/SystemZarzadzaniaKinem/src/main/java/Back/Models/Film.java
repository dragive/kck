package Back.Models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Film {
    private Integer id;
    private Date releaseDate;
    private String description;
    private String title;
    private Integer filmCategoryId;
}
