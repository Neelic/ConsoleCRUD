package org.ConsoleCRUD.service;

import org.ConsoleCRUD.repository.UserRepository;
import org.ConsoleCRUD.repository.entity.User;

public class UserService {

    private final UserRepository userRepository;
    private User currentUser = null;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean loginUser(String email, String password) {
        try {
            User user = userRepository.getUser(email);
            if (!user.getPassword().equals(password)) {
                throw new IllegalArgumentException("Incorrect password");
            }

            currentUser = user;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean registerUser(String email, String password, String name) {
        try {
            User user = new User(email, password, name);
            userRepository.addUser(user);

            currentUser = user;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean updateUser(String email, String password, String name) {
        try {
            User user = new User(email, password, name);
            userRepository.updateUser(user);

            currentUser = user;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteUser() {
        try {
            if (currentUser == null) {
                throw new IllegalArgumentException("User is not logged in");
            }

            userRepository.deleteUser(currentUser.getEmail());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        currentUser = null;
        return true;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
