package com.example.backendwebservice.post;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {

    private Map<String, Post> posts = new HashMap<>();

    public void addPost(Post post){
        posts.put(post.getTitle(), post);
    }

    public void remove(String title){
        posts.remove(title.toLowerCase());
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }

    public Post get(String title){
        return posts.get(title.toLowerCase());
    }

    public void savePost(Post post) {
        posts.put(post.getTitle().toLowerCase(), post);
    }

    public String delete(String title) {
        posts.remove(title.toLowerCase());
        return "Post has been deleted";
    }

    public Post getThisPost(String post) {
        Post thisPost = posts.get(post.toLowerCase());
        if(thisPost != null){
            return posts.get(post);
        }
        return null;
    }
}
