package org.ManageHabits.repository.entity;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class HabitTest {

    @Test
    public void testHabitInitializationWithValidParameters() {
        String name = "Exercise";
        String description = "Daily exercise routine";
        Frequency frequency = Frequency.DAILY;

        Habit habit = new Habit(name, description, frequency);

        assertEquals(name, habit.getName());
        assertEquals(description, habit.getDescription());
        assertEquals(frequency, habit.getFrequency());
        assertNotNull(habit.getCreated());
        assertFalse(habit.isCompleted());
        assertTrue(habit.getHistoryChecks().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHabitInitializationWithNotValidParameters() {
        new Habit("", "", Frequency.DAILY);

        fail("Expected an IllegalArgumentException to be thrown.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHabitInitializationWithNullParameters() {
        new Habit(null, null, null);

        fail("Expected an IllegalArgumentException to be thrown.");
    }

    @Test
    public void testAddsTodayDateToHistoryChecks() {
        Habit habit = new Habit("Exercise", "Daily exercise", Frequency.DAILY);
        habit.setCompleted(true);
        assertTrue(habit.isCompleted());
        assertTrue(habit.getHistoryChecks().contains(LocalDate.now()));
    }
}