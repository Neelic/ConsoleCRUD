package org.ConsoleCRUD.screen.user;

import org.ConsoleCRUD.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class UserInitScreenTest {

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
        TestUtils.provideInput("1");
        UserInitScreen userInitScreen = new UserInitScreen(new Scanner(System.in));

        int choice = userInitScreen.show();

        assertEquals(1, choice);
        assertEquals(UserInitScreen.FIRST_SCREEN_MESSAGE + "\r\n", outContent.toString());
    }
}