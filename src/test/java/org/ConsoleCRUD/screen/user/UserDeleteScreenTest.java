package org.ConsoleCRUD.screen.user;

import org.ConsoleCRUD.screen.Screen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class UserDeleteScreenTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void testShow() {
        UserDeleteScreen userInitScreen = new UserDeleteScreen();

        int choice = userInitScreen.show();

        assertEquals(Screen.NOT_CHOICE, choice);
        assertEquals("Вы удалили пользователя\r\n", outContent.toString());
    }
}