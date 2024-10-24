package org.ConsoleCRUD.screen.user;

import org.ConsoleCRUD.TestUtils;
import org.ConsoleCRUD.screen.Screen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.*;

public class UserAuthScreenTest {

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
        TestUtils.provideInput("a@a.a\na\na");
        UserAuthScreen userInitScreen = new UserAuthScreen(new Scanner(System.in));

        int choice = userInitScreen.show();

        assertEquals(Screen.NOT_CHOICE, choice);
        assertEquals("email:\r\npassword:\r\nname:\r\n", outContent.toString());
        assertEquals("a@a.a", userInitScreen.getEmail());
        assertEquals("a", userInitScreen.getPassword());
        assertEquals("a", userInitScreen.getName());
    }
}