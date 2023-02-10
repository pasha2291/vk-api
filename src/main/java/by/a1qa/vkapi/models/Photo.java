package by.a1qa.vkapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Photo {
    private Integer id;
    @JsonProperty("owner_id")
    private Integer ownerId;
}
