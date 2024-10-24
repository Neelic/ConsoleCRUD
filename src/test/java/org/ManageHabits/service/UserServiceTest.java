package org.ManageHabits.service;

import org.ManageHabits.repository.UserRepository;
import org.ManageHabits.repository.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class UserServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testLoginUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.when(mock.getUser("Test@test.ru")).thenReturn(user);
        UserService userService = new UserService(mock);

        assertTrue(userService.loginUser("Test@test.ru", "Test"));
        assertEquals(userService.getCurrentUser(), user);
    }

    @Test
    public void testLoginUserWithWrongPassword() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.when(mock.getUser("Test@test.ru")).thenReturn(user);
        UserService userService = new UserService(mock);

        assertFalse(userService.loginUser("Test@test.ru", "Test1"));
        assertNull(userService.getCurrentUser());
        assertEquals("Incorrect password\r\n", outContent.toString());
    }

    @Test
    public void testLoginNotExistUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mock);

        assertFalse(userService.loginUser("Test@test.ru", "Test"));
        assertNull(userService.getCurrentUser());
        assertEquals("User not found\r\n", outContent.toString());
    }

    @Test
    public void testLoginNullUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mock);

        assertFalse(userService.loginUser(null, null));
        assertNull(userService.getCurrentUser());
        assertEquals("User not found\r\n", outContent.toString());
    }

    @Test
    public void testRegisterUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mock);

        assertTrue(userService.registerUser("Test@test.ru", "Test", "Test"));
        assertEquals(userService.getCurrentUser(), new User("Test@test.ru", "Test", "Test"));
    }

    @Test
    public void testRegisterUserWithExistEmail() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        Mockito.doThrow(new IllegalArgumentException("User with this email already exists")).when(mock).addUser(Mockito.any());
        UserService userService = new UserService(mock);

        assertFalse(userService.registerUser("Test@test.ru", "Test", "Test"));
        assertNull(userService.getCurrentUser());
        assertEquals("User with this email already exists\r\n", outContent.toString());
    }

    @Test
    public void testRegisterUserWithNullEmail() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mock);

        assertFalse(userService.registerUser(null, "Test", "Test"));
        assertNull(userService.getCurrentUser());
        assertEquals("Email, password and name cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testRegisterUserWithNullPassword() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mock);

        assertFalse(userService.registerUser("Test@test.ru", null, "Test"));
        assertNull(userService.getCurrentUser());
        assertEquals("Email, password and name cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testRegisterUserWithNullName() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        UserService userService = new UserService(mock);

        assertFalse(userService.registerUser("Test@test.ru", "Test", null));
        assertNull(userService.getCurrentUser());
        assertEquals("Email, password and name cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testUpdateUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.when(mock.getUser("Test@test.ru")).thenReturn(user);
        UserService userService = new UserService(mock);
        userService.loginUser(user.getEmail(), user.getPassword());

        assertTrue(userService.updateUser("Test@test.ru", "Test", "Test"));
        assertEquals(userService.getCurrentUser(), user);
    }

    @Test
    public void testUpdateNotLoggedUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.when(mock.getUser("Test@test.ru")).thenReturn(user);
        UserService userService = new UserService(mock);

        assertFalse(userService.updateUser("Test@test.ru", "Test", "Test"));
        assertNull(userService.getCurrentUser());
        assertEquals("User is not logged in\r\n", outContent.toString());
    }

    @Test
    public void testDeleteUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.when(mock.getUser("Test@test.ru")).thenReturn(user);
        UserService userService = new UserService(mock);
        userService.loginUser(user.getEmail(), user.getPassword());

        assertTrue(userService.deleteUser());
        assertNull(userService.getCurrentUser());
    }

    @Test
    public void testDeleteNotLoggedUser() {
        UserRepository mock = Mockito.mock(UserRepository.class);
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.when(mock.getUser("Test@test.ru")).thenReturn(user);
        UserService userService = new UserService(mock);

        assertFalse(userService.deleteUser());
        assertNull(userService.getCurrentUser());
        assertEquals("User is not logged in\r\n", outContent.toString());
    }
}