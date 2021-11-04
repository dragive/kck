package Back.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Film {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("releaseDate")
    private Date releaseDate;
    @JsonProperty("description")
    private String description;
    @JsonProperty("title")
    private String title;
    @JsonProperty("filmCategoryId")
    private Integer filmCategoryId;
}
