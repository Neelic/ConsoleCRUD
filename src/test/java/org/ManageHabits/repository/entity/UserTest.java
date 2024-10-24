package org.ManageHabits.repository.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserTest {

    @Test
    public void testUserWithValidParameters() {
        User user = new User("test@example.com", "password123", "John Doe");
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password123", user.getPassword());
        assertEquals("John Doe", user.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUserWithNotValidParameters() {
        new User("", "", "");

        fail("Expected an IllegalArgumentException to be thrown.");
    }
}