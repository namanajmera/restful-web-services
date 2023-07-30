package com.naman.rest.webservices.restfulwebservices.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Nick", LocalDate.now().minusYears(29)));
        users.add(new User(3, "Ninja", LocalDate.now().minusYears(34)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id){
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }
}
