package org.ConsoleCRUD.service;

import org.ConsoleCRUD.repository.HabitRepository;
import org.ConsoleCRUD.repository.entity.Frequency;
import org.ConsoleCRUD.repository.entity.Habit;

import java.util.List;

public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public boolean createHabit(String email, String name, String description, Frequency frequency) {
        try {
            Habit habit = new Habit(name, description, frequency);
            habitRepository.createHabit(email, habit);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public List<Habit> getHabits(String email) {
        List<Habit> habits;
        try {
            habits = habitRepository.getHabits(email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return habits;
    }

    public boolean changeHabit(String email, Habit oldHabit, String name, String description, Frequency frequency) {
        try {
            Habit newHabit = new Habit(name, description, frequency);
            habitRepository.changeHabit(email, oldHabit, newHabit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteHabit(String email, Habit habit) {
        try {
            habitRepository.deleteHabit(email, habit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
