package com.example.backendwebservice.post;

import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PostRepository {
//data handler

    //The key is the name and value is Post, saves the posts
    private Map<String, Post> posts = new HashMap<>();

    //add post to the Map depend on the name(key) and the post
    public void addPost(Post post){
        posts.put(post.getTitle(), post);
    }

    //remove depend on the name, set toLowerCase if the add is both big and small letters
    public void remove(Post post){
        posts.remove(post.getTitle().toLowerCase());
    }

    //return the collection(lists)
    public Collection<Post> getPosts() {
        return posts.values();
    }

    //return the post that is the same as the name
    public Post get(String title){
        return posts.get(title.toLowerCase());
    }

    public void savePost(Post post) {
        posts.put(post.getTitle().toLowerCase(), post);
    }

//    public Post updatePost(String name, Post post){
//        post = posts.get(name);
//        return get(name, post);
//    }

    public String delete(String tile) {
        posts.remove(tile.toLowerCase());
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
