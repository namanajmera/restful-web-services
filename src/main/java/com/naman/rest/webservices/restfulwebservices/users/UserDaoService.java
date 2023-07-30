package com.naman.rest.webservices.restfulwebservices.users;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(++userCount, "Adam", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "Nick", LocalDate.now().minusYears(29)));
        users.add(new User(++userCount, "Ninja", LocalDate.now().minusYears(34)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(int id){
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }
}
