package by.a1qa.vkapi.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UploadServer {
    @JsonProperty("album_id")
    private Integer albumId;
    @JsonProperty("upload_url")
    private String uploadUrl;
    @JsonProperty("user_id")
    private Integer userId;
}
