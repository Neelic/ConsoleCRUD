package org.ConsoleCRUD.repository.entity;

import org.junit.Test;

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
}