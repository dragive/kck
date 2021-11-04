package Back.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Cinema {

    private Integer id;
    private Integer cityId;
    private String name;
    private String description;

}
