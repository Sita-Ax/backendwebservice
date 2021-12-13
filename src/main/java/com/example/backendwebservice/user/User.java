package com.example.backendwebservice.user;

import com.example.backendwebservice.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password, salt;

    private List<Post> favorites;
    private List<Post> userPosts;

    public User(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.favorites = new ArrayList<>();
        this.userPosts = new ArrayList<>();
    }
}
