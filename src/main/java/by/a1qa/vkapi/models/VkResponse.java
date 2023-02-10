package by.a1qa.vkapi.models;

import lombok.Data;

@Data
public class VkResponse <T> {
    private T response;
}
