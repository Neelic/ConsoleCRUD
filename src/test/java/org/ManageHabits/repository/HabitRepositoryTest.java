package org.ManageHabits.repository;

import org.ManageHabits.db.Storage;
import org.ManageHabits.db.StorageConf;
import org.ManageHabits.repository.entity.Frequency;
import org.ManageHabits.repository.entity.Habit;
import org.ManageHabits.repository.entity.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.ManageHabits.TestUtils.*;
import static org.junit.Assert.*;

public class HabitRepositoryTest {

    public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>(DB_IMAGE)
            .withReuse(true)
            .withDatabaseName(DB_NAME)
            .withExposedPorts(DB_PORT)
            .withUsername(DB_USERNAME)
            .withPassword(DB_PASSWORD); //TODO do not work with all run tests

    @BeforeClass
    public static void beforeClass() {
        postgreSQLContainer.start();
        Storage.setConf(new StorageConf(
                postgreSQLContainer.getJdbcUrl(), postgreSQLContainer.getUsername(), postgreSQLContainer.getPassword())
        );
    }

    @AfterClass
    public static void afterClass() {
        postgreSQLContainer.stop();
    }

    @Test
    public void testHabitRepositoryWithValidParameters() {
        HabitRepository habitRepository = new HabitRepository();
        Habit habit = new Habit("Exercise", "Daily exercise", Frequency.DAILY);
        User user = new User("test@test.ru", "test", "test");
        user.setId(1);
        habitRepository.createHabit(user, habit);

        List<Habit> habits = habitRepository.getHabits(user);

        assertNotNull(habits);
        assertEquals(3, habits.size());
        assertTrue(habits.contains(habit));
    }

    @Test
    public void testChangeHabit() {
        HabitRepository habitRepository = new HabitRepository();
        Habit oldHabit = new Habit("Exercise", "Daily exercise", Frequency.DAILY);
        Habit newHabit = new Habit("Meditation", "Mindfulness practice", Frequency.WEEKLY);
        User user = new User("user@example.com", "test", "test");
        user.setId(1);
        habitRepository.createHabit(user, oldHabit);
        habitRepository.changeHabit(user, oldHabit, newHabit);

        List<Habit> habits = habitRepository.getHabits(user);

        assertNotNull(habits);
        assertEquals(4, habits.size());
        assertTrue(habits.contains(newHabit));
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
}