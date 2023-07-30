package com.naman.rest.webservices.restfulwebservices.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    private UserDaoService userDaoService;

    private UsersRepository usersRepository;

    public UserController(UserDaoService userDaoService, UsersRepository usersRepository) {
        this.userDaoService = userDaoService;
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return usersRepository.findAll();
//        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) {
        User user = usersRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        return user;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User saved = usersRepository.save(user);
//        User saved = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
