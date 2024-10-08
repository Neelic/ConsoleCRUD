package org.ConsoleCRUD.service;

import org.ConsoleCRUD.repository.UserRepository;
import org.ConsoleCRUD.repository.entity.User;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User loginUser(String email, String password) {
        User user = null;
        try {
            user = userRepository.getUser(email);
            if (!user.getPassword().equals(password)) {
                throw new IllegalArgumentException("Incorrect password");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public User registerUser(String email, String password) {
        User user = null;
        try {
            user = new User(email, password);
            userRepository.addUser(user);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        return user;
    }

    public void updateUser(String email, String password) {
        try {
            User user = new User(email, password);
            userRepository.updateUser(user);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteUser(String email) {
        try {
            userRepository.deleteUser(email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
