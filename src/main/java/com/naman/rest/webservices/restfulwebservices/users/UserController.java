package com.naman.rest.webservices.restfulwebservices.users;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id){
        return userDaoService.findById(id);
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user){
        userDaoService.save(user);
    }
}
