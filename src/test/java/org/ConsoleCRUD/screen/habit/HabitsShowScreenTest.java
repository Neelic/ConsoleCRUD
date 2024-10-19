package org.ConsoleCRUD.screen.habit;

import org.ConsoleCRUD.TestUtils;
import org.ConsoleCRUD.repository.entity.Frequency;
import org.ConsoleCRUD.repository.entity.Habit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class HabitsShowScreenTest {

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
    public void testShowWithoutHabits() {
        TestUtils.provideInput("1");
        HabitsShowScreen userInitScreen = new HabitsShowScreen(new Scanner(System.in));

        int choice = userInitScreen.show();

        assertEquals(0, choice);
        assertEquals(HabitsShowScreen.FIRST_MESSAGE + "\r\n", outContent.toString());
    }

    @Test
    public void testShowWithHabits() {
        TestUtils.provideInput("1");
        HabitsShowScreen userInitScreen = new HabitsShowScreen(new Scanner(System.in));
        userInitScreen.setHabits(List.of(
                new Habit("testName", "testDescription", Frequency.DAILY),
                new Habit("testName2", "testDescription2", Frequency.WEEKLY)));

        int choice = userInitScreen.show();

        assertEquals(1, choice);
        assertEquals(HabitsShowScreen.FIRST_MESSAGE + "\r\n1 - testName, testDescription, ежедневно, false\r\n2 - testName2, testDescription2, еженедельно, false\r\n",
                outContent.toString());
    }
}