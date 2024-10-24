package org.ManageHabits.service;

import org.ManageHabits.repository.UserRepository;
import org.ManageHabits.repository.dto.UserDTO;
import org.ManageHabits.repository.entity.User;
import org.ManageHabits.repository.mappers.UserMapper;

public class UserService {

    private final UserRepository userRepository;
    private User currentUser = null;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void loginUser(UserDTO dto) {
        User user = userRepository.getUser(dto.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!user.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        currentUser = user;
    }

    public void registerUser(UserDTO dto) {
        if (dto.getName() == null || dto.getPassword() == null || dto.getEmail() == null) {
            throw new IllegalArgumentException("Name, password and email cannot be null");
        }

        if (dto.getName().isEmpty() || dto.getPassword().isEmpty() || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Name, password and email cannot be empty");
        }

        userRepository.addUser(UserMapper.INSTANCE.toEntity(dto));
    }

    public void updateUser(UserDTO dto) {
        if (dto.getId() == 0 || dto.getName() == null || dto.getPassword() == null || dto.getEmail() == null) {
            throw new IllegalArgumentException("Name, password and email cannot be null");
        }

        if (dto.getName().isEmpty() || dto.getPassword().isEmpty() || dto.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Name, password and email cannot be empty");
        }

        if (!dto.getEmail().matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]+$")) {
            throw new IllegalArgumentException("Invalid email format, it should contain at least one '@' and at least one '.'");
        }

        User user = UserMapper.INSTANCE.toEntity(dto);
        userRepository.updateUser(user);

    }

    public void deleteUser(UserDTO dto) {
        if (dto.getId() == 0) {
            throw new IllegalArgumentException("Invalid user id");
        }

        userRepository.deleteUser(currentUser);
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
