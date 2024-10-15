package org.ConsoleCRUD.repository;

import org.ConsoleCRUD.repository.entity.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserRepositoryTest {

    @Test
    public void test_add_user_with_unique_email() {
        UserRepository userRepository = new UserRepository();
        User user = new User("unique@example.com", "password123", "John Doe");
        userRepository.addUser(user);
        assertEquals(user, userRepository.getUser("unique@example.com"));
    }

    @Test
    public void test_retrieve_existing_user_by_email() {
        UserRepository userRepository = new UserRepository();
        User user = new User("existing@example.com", "password123", "Jane Doe");
        userRepository.addUser(user);
        User retrievedUser = userRepository.getUser("existing@example.com");
        assertEquals(user, retrievedUser);
    }

    @Test
    public void test_delete_existing_user_by_email() {
        UserRepository userRepository = new UserRepository();
        User user = new User("delete@example.com", "password123", "John Smith");
        userRepository.addUser(user);
        userRepository.deleteUser("delete@example.com");
        assertNull(userRepository.getUser("delete@example.com"));
    }

    @Test
    public void test_update_existing_user_with_valid_data() {
        UserRepository userRepository = new UserRepository();
        User user = new User("update@example.com", "password123", "Alice Doe");
        userRepository.addUser(user);
        User updatedUser = new User("update@example.com", "newpassword123", "Alice Doe");
        userRepository.updateUser(updatedUser);
        assertEquals(updatedUser, userRepository.getUser("update@example.com"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_add_user_with_existing_email_throws_exception() {
        UserRepository userRepository = new UserRepository();
        User user1 = new User("duplicate@example.com", "password123", "Bob Doe");
        User user2 = new User("duplicate@example.com", "password456", "Charlie Doe");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
    }

    @Test
    public void test_retrieve_nonexistent_user_returns_null() {
        UserRepository userRepository = new UserRepository();
        assertNull(userRepository.getUser("nonexistent@example.com"));
    }

    @Test
    public void test_delete_nonexistent_user_does_not_throw_exception() {
        UserRepository userRepository = new UserRepository();

        userRepository.deleteUser("nonexistent@example.com");
    }
}