package org.ConsoleCRUD.repository;

import org.ConsoleCRUD.repository.entity.User;

import java.util.HashMap;

public class UserRepository {

    private final static HashMap<String, User> users = new HashMap<>();

    public void addUser(User user) {
        if (users.containsKey(user.getEmail())) {
            throw new IllegalArgumentException("User already exists");
        }

        users.put(user.getEmail(), user);
    }

    public User getUser(String email) {
        return users.get(email);
    }

    public void deleteUser(String email) {
        users.remove(email);
    }

    public void updateUser(User user) {
        if (!users.containsKey(user.getEmail())) {
            throw new IllegalArgumentException("User does not exist");
        }

        users.put(user.getEmail(), user);
    }
}
