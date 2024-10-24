package org.ManageHabits.screen.habit;

import org.ManageHabits.screen.Screen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class HabitDeleteScreenTest {

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
        HabitDeleteScreen userInitScreen = new HabitDeleteScreen();

        int choice = userInitScreen.show();

        assertEquals(Screen.NOT_CHOICE, choice);
        assertEquals("Вы удалили привычку\r\n", outContent.toString());
    }
}