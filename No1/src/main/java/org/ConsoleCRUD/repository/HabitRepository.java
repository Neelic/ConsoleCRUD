package org.ConsoleCRUD.repository;

import org.ConsoleCRUD.repository.entity.Habit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HabitRepository {

    private final static HashMap<String, ArrayList<Habit>> habits = new HashMap<>();

    public List<Habit> getHabits(String email) {
        if (email == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        habits.computeIfAbsent(email, key -> new ArrayList<>());
        return habits.get(email);
    }

    public void createHabit(String email, Habit habit) {
        if (email == null || habit == null) {
            throw new IllegalArgumentException("User and habit cannot be null");
        }

        habits.computeIfAbsent(email, key -> new ArrayList<>()).add(habit);
    }

    public void changeHabit(String email, Habit oldHabit, Habit newHabit) {
        if (email == null || newHabit == null || oldHabit == null) {
            throw new IllegalArgumentException("User and habits cannot be null");
        }

        if (!habits.containsKey(email)) {
            throw new IllegalArgumentException("User does not have any habits");
        }

        if (!habits.get(email).contains(oldHabit)) {
            throw new IllegalArgumentException("User does not have this habit");
        }

        habits.get(email).remove(oldHabit);
        habits.get(email).add(newHabit);
    }

    public void deleteHabit(String email, Habit habit) {
        if (email == null || habit == null) {
            throw new IllegalArgumentException("User and habit cannot be null");
        }

        if (!habits.containsKey(email)) {
            throw new IllegalArgumentException("User does not have any habits");
        }

        if (!habits.get(email).contains(habit)) {
            throw new IllegalArgumentException("User does not have this habit");
        }

        habits.get(email).remove(habit);
    }
}
