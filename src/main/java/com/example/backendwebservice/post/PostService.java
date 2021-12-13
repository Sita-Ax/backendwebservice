package com.example.backendwebservice.post;

import com.example.backendwebservice.dto.PostCreate;
import com.example.backendwebservice.user.User;
import com.example.backendwebservice.user.UserRepository;
import com.example.backendwebservice.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.ArrayList;
import java.util.Collection;

@Service
@AllArgsConstructor
public class PostService {
//contains the logic i the system
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;

    //create, save and return the post
    public PostCreate createPost(@RequestHeader("token") String token, @RequestBody Post post) {
        User user = userService.validate(token);
        if(user == null){
            return null;
        }
        Post exists = postRepository.get(user.getUsername());
        if (exists == null) {
            PostCreate postCreate = new PostCreate(post.getTitle(), post.getContent());
            postCreate.setId(post.getId());
            postCreate.setTitle(post.getTitle());
            postCreate.setContent(post.getContent());
            postCreate.setCategory(post.getCategory());
            postCreate.setUsername(post.getUsername());
            postRepository.savePost(post);
            System.out.println(postCreate + "PostCreate");
            return postCreate;
        } else {
            return null;
        }
    }

    public Collection<PostCreate> getAllPosts(){
        Collection<Post> posts = postRepository.getPosts();
        Collection<PostCreate> postCreates = new ArrayList<>();
        for(Post post : posts){
            postCreates.add(new PostCreate(post.getTitle(), post.getContent()));
        }
        return postCreates;
    }

    public Post getInfo(String name){
        return postRepository.get(name);
    }

    public PostCreate getPost(String title) {
        Post exists = postRepository.get(title);
        if (exists != null) {
            PostCreate postCreate = new PostCreate(exists.getTitle(), exists.getContent());
            return postCreate;
        } else {
            return null;
        }
    }

    public String deletePost(String title, Post username){
        Post exists = postRepository.get(title);
        if (exists != null) {
            if (username.equals(exists.getUsername())) {
                return postRepository.delete(title);
            } else {
                return "Nothing to delete";
            }
        } else {
            return "This post can not be deleted by you";
        }
    }

    public boolean updatePost(String title, Post updatePost){
        Post post = postRepository.get(title);
        if(post == null)
            return false;
        post.setTitle(updatePost.getTitle());
        post.setContent(updatePost.getContent());
        post.setId(updatePost.getTitle());
        post.setCategory(updatePost.getCategory());
        return true;
    }

    public int addFavorite(User user, String title) {
        Post post = postRepository.get(title);
        if (post == null)
            return 1;

        user.getFavorites().add(post);
        return 0;
    }

}