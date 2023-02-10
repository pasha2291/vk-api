package by.a1qa.vkapi.models;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UserList {
    private Integer count;
    private ArrayList<User> users;
}
