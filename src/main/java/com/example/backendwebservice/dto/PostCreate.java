package com.example.backendwebservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PostCreate {
    private String id, title, content, category, username;
    private String hyperLink;


    public PostCreate(String title, String content, String category, String username) {
        this.id = title;
        this.title = title;
        this.content = content;
        this.category = category;
        this.username = username;
        this.hyperLink = "/post/" + title;
    }

}

