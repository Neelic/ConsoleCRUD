package org.ConsoleCRUD.repository.entity;

public class User {

    private final String email;
    private final String password;
    private final String name;

    public User(String email, String password, String name) {
        if (email == null || password == null || name == null) {
            throw new IllegalArgumentException("Email, password and name cannot be null");
        }

        if (email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty");
        }

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]+$")) {
            throw new IllegalArgumentException("Неверный формат email, он должен содержать хотя бы один символ," +
                    " символ '@', хотя бы один символ до и псоле точки");
        }

        if (name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }

        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
