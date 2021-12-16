package com.example.backendwebservice.post;

import com.example.backendwebservice.dto.PostCreate;
import com.example.backendwebservice.user.User;
import com.example.backendwebservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/post")
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {
//presentation layer
    @Autowired
    UserService userService;
    @Autowired
    PostService postService;

    //this is working creates a token, creates a new object Post //return Post(title-table), content and id but the likes is null
    @PutMapping("/create")
    public PostCreate createPost(@RequestHeader("token") String token, @RequestBody Post post, HttpServletResponse response) {
        if (userService.validate(token) == null) {
            response.setStatus(401);
            return null;
        }
        System.out.println(post + "post");
        if(post.getTitle() == null || post.getContent() == null|| post.getUsername() == null|| post.getCategory() == null){
            response.setStatus(400);
        }
        return postService.createPost(token, post);
    }

    //works an array with title, content, id, likes, nolikes
    @GetMapping("/all")
    public Collection<PostCreate> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/info/{title}")
    public PostCreate getPost(@RequestHeader("token") String token, @RequestBody Post post, @PathVariable String title, HttpServletResponse response) {
        if (token != null) {
            PostCreate postCreate = postService.getPost(title);
            if (postCreate != null) {
                response.setStatus(200);
                return postCreate;
            } else {
                response.setStatus(401);
                return null;
            }
        } else {
            return null;
        }
    }

    @GetMapping("/favorites")
    public List<Post> getFavorites(@RequestHeader("token") String token, HttpServletResponse response) {
        User user = userService.validate(token);
        if (user == null) {
            response.setStatus(401);
            return null;
        }
        return user.getFavorites();
    }

    @PutMapping("/add-favorite")
    public String addFavorites(@RequestHeader("token") String token, @RequestBody String title, HttpServletResponse response) {
        User user = userService.validate(token);
        if (token == null) {
            response.setStatus(401);
            return null;
        }
        int result = postService.addFavorite(user, title);
        switch (result) {
            case 1:
                response.setStatus(404);
                return "There is no post with that title";
            case 0:
                return "Post has been added to user's favorites";
            default:
                response.setStatus(500);
                return "Something went wrong.";
        }
    }

    @PatchMapping("/update/{title}")
    public boolean updatePost(@RequestHeader("token") String token, @PathVariable String title, @RequestBody Post updatePost, HttpServletResponse response) {
        if(token == null){
            if(!postService.updatePost(title, updatePost)){
                response.setStatus(404);
                return false;
            }
        }
        return postService.updatePost(title, updatePost);
    }

    @DeleteMapping("/delete/{title}")
    public String deletePost(@RequestHeader("token") String token, @PathVariable String title, HttpServletResponse response) {
        if(!postService.deletePost(title)){
            response.setStatus(404);
            return "No";
        }
        return "Yes it is deleted!";
    }
}