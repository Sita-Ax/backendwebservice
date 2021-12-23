package com.example.backendwebservice.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id, title, content, category, username;
    private List<Integer> likes;
    private List<Integer> noLikes;

    public Post(String title, String content, String category) {
        this.id = title;
        this.title = title;
        this.content = content;
        this.category = category;
        this.username = username;
        this.likes = new ArrayList<>();
        this.noLikes = new ArrayList<>();
    }


}
