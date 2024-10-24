package org.ManageHabits.screen.habit;

import org.ManageHabits.TestUtils;
import org.ManageHabits.repository.entity.Frequency;
import org.ManageHabits.repository.entity.Habit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HabitsStatScreenTest {

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
        HabitsStatScreen screen = new HabitsStatScreen();
        screen.show();

        assertEquals("Статистика привычек:\r\n", outContent.toString());
    }

    @Test
    public void testShowWithHabits() {
        TestUtils.provideInput("1");
        HabitsStatScreen screen = new HabitsStatScreen();
        screen.setHabits(List.of(
                new Habit("testName", "testDescription", Frequency.DAILY),
                new Habit("testName2", "testDescription2", Frequency.WEEKLY)));
        screen.show();

        assertEquals("""
                Статистика привычек:\r
                1 - testName, testDescription, ежедневно, Выполнение: 0, Стрик: 0\r
                2 - testName2, testDescription2, еженедельно, Выполнение: 0, Стрик: 0\r
                """, outContent.toString());
    }
}