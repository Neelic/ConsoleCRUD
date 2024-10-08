package org.ConsoleCRUD.repository.entity;

public class User {

    private final String email;
    private final String password;

    public User(String email, String password) {
        if (email == null || password == null) {
            throw new IllegalArgumentException("Email and password cannot be null");
        }

        if (email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password cannot be empty");
        }

        if (!email.matches("^[\\w-.]+@([\\w-]+\\.)+[\\w-]{1,4}$")) {
            throw new IllegalArgumentException("Email must contain @");
        }

        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
