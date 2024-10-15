package org.ConsoleCRUD.repository;

import org.ConsoleCRUD.repository.entity.Frequency;
import org.ConsoleCRUD.repository.entity.Habit;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HabitRepositoryTest {

    @Test
    public void testHabitRepositoryWithValidParameters() {
        HabitRepository habitRepository = new HabitRepository();
        String email = "user@example.com";
        Habit habit = new Habit("Exercise", "Daily exercise", Frequency.DAILY);
        habitRepository.createHabit(email, habit);

        List<Habit> habits = habitRepository.getHabits(email);

        assertNotNull(habits);
        assertEquals(1, habits.size());
        assertEquals(habit, habits.getFirst());
    }

    @Test
    public void testChangeHabit() {
        HabitRepository habitRepository = new HabitRepository();
        String email = "user@example.com";
        Habit oldHabit = new Habit("Exercise", "Daily exercise", Frequency.DAILY);
        Habit newHabit = new Habit("Meditation", "Mindfulness practice", Frequency.WEEKLY);
        habitRepository.createHabit(email, oldHabit);
        habitRepository.changeHabit(email, oldHabit, newHabit);

        List<Habit> habits = habitRepository.getHabits(email);

        assertNotNull(habits);
        assertEquals(1, habits.size());
        assertEquals(newHabit, habits.getFirst());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHabitRepositoryWithNotValidParameters() {
        HabitRepository habitRepository = new HabitRepository();

        habitRepository.getHabits(null);

        fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleNullUserEmailWhenCreatingHabit() {
        HabitRepository habitRepository = new HabitRepository();
        Habit habit = new Habit("Exercise", "Daily workout", Frequency.DAILY);

        habitRepository.createHabit(null, habit);
        fail("Expected IllegalArgumentException to be thrown");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleNullUserEmailWhenChangingHabit() {
        HabitRepository habitRepository = new HabitRepository();
        Habit oldHabit = new Habit("Exercise", "Daily workout", Frequency.DAILY);
        Habit newHabit = new Habit("Meditation", "Mindfulness practice", Frequency.WEEKLY);

        habitRepository.changeHabit(null, oldHabit, newHabit);
        fail("Expected IllegalArgumentException to be thrown");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleNullUserEmailWhenDeletingHabit() {
        HabitRepository habitRepository = new HabitRepository();
        Habit habit = new Habit("Exercise", "Daily workout", Frequency.DAILY);

        habitRepository.deleteHabit(null, habit);
        fail("Expected IllegalArgumentException to be thrown");
    }
}