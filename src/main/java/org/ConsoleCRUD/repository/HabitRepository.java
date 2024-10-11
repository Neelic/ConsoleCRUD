package org.ConsoleCRUD.repository;

import org.ConsoleCRUD.repository.entity.Habit;
import org.ConsoleCRUD.repository.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HabitRepository {

    private final static HashMap<User, ArrayList<Habit>> habits = new HashMap<>();

    public List<Habit> getHabits(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        return habits.get(user);
    }

    public void createHabit(User user, Habit habit) {
        if (user == null || habit == null) {
            throw new IllegalArgumentException("User and habit cannot be null");
        }

        habits.computeIfAbsent(user, key -> new ArrayList<>()).add(habit);
    }

    public void changeHabit(User user, Habit oldHabit, Habit newHabit) {
        if (user == null || newHabit == null || oldHabit == null) {
            throw new IllegalArgumentException("User and habits cannot be null");
        }

        if (!habits.containsKey(user)) {
            throw new IllegalArgumentException("User does not have any habits");
        }

        if (!habits.get(user).contains(oldHabit)) {
            throw new IllegalArgumentException("User does not have this habit");
        }

        habits.get(user).remove(oldHabit);
        habits.get(user).add(newHabit);
    }

    public void deleteHabit(User user, Habit habit) {
        if (user == null || habit == null) {
            throw new IllegalArgumentException("User and habit cannot be null");
        }

        if (!habits.containsKey(user)) {
            throw new IllegalArgumentException("User does not have any habits");
        }

        if (!habits.get(user).contains(habit)) {
            throw new IllegalArgumentException("User does not have this habit");
        }

        habits.get(user).remove(habit);
    }
}
