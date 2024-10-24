package org.ConsoleCRUD.repository;

import org.ConsoleCRUD.db.Storage;
import org.ConsoleCRUD.db.StorageConf;
import org.ConsoleCRUD.repository.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import static org.ConsoleCRUD.TestUtils.*;
import static org.ConsoleCRUD.TestUtils.DB_PASSWORD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserRepositoryTest {

    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DB_IMAGE)
            .withReuse(true)
            .withDatabaseName(DB_NAME)
            .withExposedPorts(DB_PORT, DB_PORT)
            .withUsername(DB_USERNAME)
            .withPassword(DB_PASSWORD); //TODO do not work with all run tests

    @BeforeClass
    public static void beforeClass() {
        postgreSQLContainer.start();
        Storage.setConf(new StorageConf(
                postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword())
        );
    }

    @AfterClass
    public static void afterClass() {
        postgreSQLContainer.stop();
    }

    @Test
    public void testAddUserWithUniqueEmail() {
        UserRepository userRepository = new UserRepository();
        User user = new User("unique@example.com", "password123", "John Doe");
        userRepository.addUser(user);
        assertEquals(user, userRepository.getUser("unique@example.com"));
    }

    @Test
    public void testRetrieveExistingUserByEmail() {
        UserRepository userRepository = new UserRepository();
        User user = new User("existing@example.com", "password123", "Jane Doe");
        userRepository.addUser(user);
        User retrievedUser = userRepository.getUser("existing@example.com");
        assertEquals(user, retrievedUser);
    }

    @Test
    public void testDeleteExistingUserByEmail() {
        UserRepository userRepository = new UserRepository();
        User user = new User("delete@example.com", "password123", "John Smith");
        userRepository.addUser(user);
        userRepository.deleteUser(user);
        assertNull(userRepository.getUser("delete@example.com"));
    }

    @Test
    public void testUpdateExistingUserWithValidData() {
        UserRepository userRepository = new UserRepository();
        User user = new User("update@example.com", "password123", "Alice Doe");
        userRepository.addUser(user);
        User updatedUser = new User("update@example.com", "newpassword123", "Alice Doe", user.getId());
        userRepository.updateUser(updatedUser);

        assertEquals(updatedUser, userRepository.getUser(updatedUser.getEmail()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddUserWithExistingEmailThrowsException() {
        UserRepository userRepository = new UserRepository();
        User user1 = new User("duplicate@example.com", "password123", "Bob Doe");
        User user2 = new User("duplicate@example.com", "password456", "Charlie Doe");
        userRepository.addUser(user1);
        userRepository.addUser(user2);
    }

    @Test
    public void testRetrieveNonexistentUserReturnsNull() {
        UserRepository userRepository = new UserRepository();
        assertNull(userRepository.getUser("nonexistent@example.com"));
    }

    @Test
    public void testDeleteNonexistentUserDoesNotThrowException() {
        UserRepository userRepository = new UserRepository();

        userRepository.deleteUser(new User("nonexistent@example.com", "password123", "John Doe"));
    }
}