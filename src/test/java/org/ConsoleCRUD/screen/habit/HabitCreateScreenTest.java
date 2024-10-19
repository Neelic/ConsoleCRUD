package org.ConsoleCRUD.screen.habit;

import org.ConsoleCRUD.TestUtils;
import org.ConsoleCRUD.repository.entity.Frequency;
import org.ConsoleCRUD.screen.Screen;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class HabitCreateScreenTest {

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
        TestUtils.provideInput("testName\ntestDescription\n1");
        HabitCreateScreen screen = new HabitCreateScreen(new Scanner(System.in));

        int choice = screen.show();

        assertEquals(Screen.NOT_CHOICE, choice);
        assertEquals("""
                Введите имя привычки:\r
                Введите описание привычки:\r
                Введите частоту повторений привычки:\r
                1 - ежедневно\r
                2 - еженедельно\r
                3 - ежемесячно\r
                """, outContent.toString());
        assertEquals("testName", screen.getName());
        assertEquals("testDescription", screen.getDescription());
        assertEquals(Frequency.DAILY, screen.getFrequency());
    }
}