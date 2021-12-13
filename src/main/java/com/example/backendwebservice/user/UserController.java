package com.example.backendwebservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserService userService;

    //Create a user
    @PutMapping("/register")
    public String registerUser(@RequestBody User user, HttpServletResponse response) {
        int choice = userService.registerUser(user);
        switch (choice) {
            case 1:
                response.setStatus(403);
                return "There is already a user with that username";
            case 0:
                response.setStatus(200);
                return "User has been registered.";
            default:
                response.setStatus(500);
                return "Something went wrong.";
        }
    }

    //Get the username and password ok
    @PostMapping("/login")
    public String login(@RequestHeader("username") String username, @RequestHeader("password") String password, HttpServletResponse response) {
        String token = userService.login(username, password);
        if (token == null) {
            response.setStatus(406);
            return "This is not acceptable!";
        }
        return token;
    }

    //Get 200 OK and the text ///DOESN'T WORK
    @PostMapping("/logout")
    public void logout(@RequestHeader("token") String token) {
        userService.logout(token);
    }

    //Get all users ok
    @GetMapping("/all")
    public Collection<User> getUsers() {
        return userService.getAll();
    }

}
