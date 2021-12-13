package com.example.backendwebservice.post;

import com.example.backendwebservice.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private String id, title, content, username;
    //this is an enum
    private Category category;
    private List<Integer> likes;
    private List<Integer> noLikes;

    public Post(String title, String content) {
        this.id = title;
        this.title = title;
        this.content = content;
        this.category = category;
        this.username = username;
        this.likes = new ArrayList<>();
        this.noLikes = new ArrayList<>();
    }


}
