package Back.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class Cinema {

    private Integer id;
    private Integer cityId;
    private String name;
    private String description;

}
