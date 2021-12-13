package com.example.backendwebservice.user;

import com.example.backendwebservice.post.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.example.backendwebservice.encrypt.PasswordEncrypt.hashPassword;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepository postRepository;

    Map<String, User> tokens = new HashMap<>();

    @Autowired
    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    //existing take the username and password that is hashed
    public int registerUser(User user) {
        User existing = userRepository.getUser(user.getUsername());
        if (existing != null)
            return 1;
        String salt = generateSalt();
        String hash = hashPassword(user.getPassword(), salt);
        assert false;
        userRepository.saveUser(new User(user.getUsername(), hash, salt));
        return 0;
    }

    //loggedIn with username and password,
    public String login(String username, String password) {
        User user = userRepository.findUser(username);
        if (user == null)
            return null;
        String salt = user.getSalt();
        String hash = hashPassword(password, salt);
        if (!user.getPassword().equals(hash))
            return null;
        String token = UUID.randomUUID().toString();
        tokens.put(token, user);
        return token;
    }

    public void logout(String token) {
        tokens.remove(token);
    }

    public User validate(String token) {
        return tokens.get(token);
    }

    public static String generateSalt() {
        return Math.random() + "jdekjdke" + Math.random() + ((char) (Math.random() * 100));
    }

    //get one user
    public User getUser(String username) {
        return userRepository.getUser(username);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

}