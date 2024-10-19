package org.ConsoleCRUD.service;

import org.ConsoleCRUD.repository.HabitRepository;
import org.ConsoleCRUD.repository.entity.Frequency;
import org.ConsoleCRUD.repository.entity.Habit;
import org.ConsoleCRUD.repository.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;

public class HabitServiceTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(System.out);
        System.setErr(System.err);
    }

    @Test
    public void testAddHabit() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        User user = new User("Test@test.ru", "Test", "Test");

        assertTrue(habitService.createHabit(user, "Test", "Test", Frequency.DAILY));
    }

    @Test
    public void testAddHabitWithNullUser() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        Mockito.doThrow(new IllegalArgumentException("User cannot be null")).when(mock).createHabit(nullable(User.class), any(Habit.class));

        habitService.createHabit(null, "Test", "Test", Frequency.DAILY);

        assertEquals("User cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testAddHabitWithNullHabit() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.doThrow(new IllegalArgumentException()).when(mock).createHabit(eq(user), any(Habit.class));

        habitService.createHabit(user, null, null, null);

        assertEquals("Name, description and frequency cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testGetHabits() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        List<Habit> habits = new ArrayList<>();
        User user = new User("Test@test.ru", "Test", "Test");
        Mockito.when(mock.getHabits(eq(user))).thenReturn(habits);
        HabitService habitService = new HabitService(mock);

        assertEquals(habitService.getHabits(user), habits);
    }

    @Test
    public void testGetHabitsWithNullUser() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        Mockito.doThrow(new IllegalArgumentException("User cannot be null")).when(mock).getHabits(nullable(User.class));

        List<Habit> result = habitService.getHabits(null);

        assertNull(result);
        assertEquals("User cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testChangeHabit() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        Habit oldHabit = new Habit("Test2", "Test2", Frequency.MONTHLY);
        User user = new User("Test@test.ru", "Test", "Test");

        assertTrue(habitService.changeHabit(user, oldHabit, "Test", "Test", Frequency.DAILY));
    }

    @Test
    public void testChangeHabitsWithNullUser() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        Mockito.doThrow(new IllegalArgumentException("User cannot be null")).when(mock).changeHabit(nullable(User.class), any(Habit.class), any(Habit.class));

        boolean result = habitService.changeHabit(null, new Habit("Test", "Test", Frequency.MONTHLY), "Test", "Test", Frequency.MONTHLY);

        assertFalse(result);
        assertEquals("User cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testCheckHabit() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        User user = new User("Test@test.ru", "Test", "Test");
        Habit habit = new Habit("Test", "Test", Frequency.MONTHLY);

        assertTrue(habitService.checkHabit(user, habit));
    }

    @Test
    public void testCheckHabitsWithNullUser() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        Mockito.doThrow(new IllegalArgumentException("User cannot be null")).when(mock).changeHabit(nullable(User.class), any(Habit.class), any(Habit.class));

        boolean result = habitService.checkHabit(null, new Habit("Test", "Test", Frequency.MONTHLY));

        assertFalse(result);
        assertEquals("User cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testDeleteHabit() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        Habit Habit = new Habit("Test", "Test", Frequency.MONTHLY);

        assertTrue(habitService.deleteHabit(Habit));
    }

    @Test
    public void testDeleteHabitsWithNullHabit() {
        HabitRepository mock = Mockito.mock(HabitRepository.class);
        HabitService habitService = new HabitService(mock);
        Mockito.doThrow(new IllegalArgumentException("User cannot be null")).when(mock).deleteHabit(nullable(Habit.class));

        boolean result = habitService.deleteHabit(null);

        assertFalse(result);
        assertEquals("User cannot be null\r\n", outContent.toString());
    }

    @Test
    public void testShowZeroStatisticOfHabit() {
        Habit habit = new Habit("Test", "Test", Frequency.MONTHLY);
        int expected = 0;

        assertEquals(expected, HabitService.showStatisticOfHabit(habit));
    }

    @Test
    public void testShowStatisticOfHabit() {
        Habit habit = new Habit("Test", "Test", Frequency.MONTHLY);
        habit.getHistoryChecks().add(LocalDate.now());
        int expected = 1;

        assertEquals(expected, HabitService.showStatisticOfHabit(habit));
    }

    @Test
    public void testStreakOf1() {
        Habit habit = new Habit("Test", "Test", Frequency.DAILY);
        habit.getHistoryChecks().add(LocalDate.now());
        int expected = 1;

        assertEquals(expected, HabitService.showStreakOfHabit(habit));
    }

    @Test
    public void testStreakOfMany() {
        Habit habit = new Habit("Test", "Test", Frequency.MONTHLY);
        habit.getHistoryChecks().add(LocalDate.now());
        habit.getHistoryChecks().add(LocalDate.now().plusMonths(1));
        habit.getHistoryChecks().add(LocalDate.now().plusMonths(2));
        habit.getHistoryChecks().add(LocalDate.now().plusMonths(3));
        habit.getHistoryChecks().add(LocalDate.now().plusMonths(4));
        habit.getHistoryChecks().add(LocalDate.now().plusMonths(5));
        int expected = 5;

        assertEquals(expected, HabitService.showStreakOfHabit(habit));
    }
}