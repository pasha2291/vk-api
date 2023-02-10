package by.a1qa.vkapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Post {
    @JsonProperty("post_id")
    private Integer postId;
}
