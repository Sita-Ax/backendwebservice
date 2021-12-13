package com.example.backendwebservice.dto;

import com.example.backendwebservice.Category;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PostCreate {
    private String id, title, content, username;
    //makes automatic an enum
    private Category category;
    private String hyperLink;


    public PostCreate(String title, String content) {
        this.id = title;
        this.title = title;
        this.content = content;
        this.category = category;
        this.username = username;
        this.hyperLink = "/post/" + title;
    }

}

