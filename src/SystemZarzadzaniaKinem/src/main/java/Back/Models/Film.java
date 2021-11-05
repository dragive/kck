package Back.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;

@Data
public class Film {
//    @JsonProperty("id")
    private Integer id;
//    @JsonProperty("releaseDate")
    private Date releaseDate;
//    @JsonProperty("description")
    private String description;
//    @JsonProperty("title")
    private String name;
//    @JsonProperty("filmCategoryId")
    private Integer filmCategoryId;


}
