package com.naman.rest.webservices.restfulwebservices.users;

import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/social")
public class UserController {


    private UsersRepository usersRepository;

    public UserController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> getUserById(@PathVariable int id) {
        User user = usersRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("id:" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;

    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User saved = usersRepository.save(user);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteById(@PathVariable int id){
        usersRepository.deleteById(id);
    }
}
