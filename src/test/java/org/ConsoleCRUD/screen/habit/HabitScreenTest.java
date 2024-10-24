package org.ConsoleCRUD.screen.habit;

import org.ConsoleCRUD.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class HabitScreenTest {

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
        HabitScreen userInitScreen = new HabitScreen(new Scanner(System.in));

        int choice = userInitScreen.show();

        assertEquals(1, choice);
        assertEquals(HabitScreen.FIRST_MESSAGE + "\r\n", outContent.toString());
    }
}