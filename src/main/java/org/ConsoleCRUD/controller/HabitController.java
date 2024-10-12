package org.ConsoleCRUD.controller;

import org.ConsoleCRUD.repository.entity.Frequency;
import org.ConsoleCRUD.repository.entity.Habit;
import org.ConsoleCRUD.repository.entity.User;
import org.ConsoleCRUD.screen.ScreenContainer;
import org.ConsoleCRUD.screen.habit.HabitCreateScreen;
import org.ConsoleCRUD.screen.habit.HabitScreen;
import org.ConsoleCRUD.screen.habit.HabitsShowScreen;
import org.ConsoleCRUD.service.HabitService;

public class HabitController {

    private final HabitService habitService;
    private final ScreenContainer screenContainer;

    public HabitController(HabitService habitService, ScreenContainer screenContainer) {
        this.habitService = habitService;
        this.screenContainer = screenContainer;
    }

    public void createHabit(User user) {
        HabitCreateScreen screen = (HabitCreateScreen) screenContainer.getScreen(ScreenContainer.HABIT_CREATE_SCREEN);
        screen.show();

        String name = screen.getName();
        String description = screen.getDescription();
        Frequency frequency = screen.getFrequency();

        if (habitService.createHabit(user.getEmail(), name, description, frequency)) {
            showHabits(user);
        }
    }

    public void showHabit(User user, int habitNumber) {
        int choice = screenContainer.getScreen(ScreenContainer.HABIT_SCREEN).show();
        Habit habit = habitService.getHabits(user.getEmail()).get(habitNumber - 1);

        switch (choice) {
            case HabitScreen.EXIT_CHOICE -> showHabits(user);
            case HabitScreen.CHANGE_HABIT_CHOICE -> changeHabit(user, habit);
            case HabitScreen.DELETE_HABIT_CHOICE -> deleteHabit(user, habit);
        }
    }

    public void showHabits(User user) {
        HabitsShowScreen screen = (HabitsShowScreen) screenContainer.getScreen(ScreenContainer.HABITS_SHOW_SCREEN);
        screen.setHabits(habitService.getHabits(user.getEmail()));
        int choice = screen.show();

        if (choice != HabitsShowScreen.EXIT_CHOICE) {
            showHabit(user, choice);
        }
    }

    public void changeHabit(User user, Habit oldHabit) {
        HabitCreateScreen screen = (HabitCreateScreen) screenContainer.getScreen(ScreenContainer.HABIT_CREATE_SCREEN);
        screen.show();
        String name = screen.getName();
        String description = screen.getDescription();
        Frequency frequency = screen.getFrequency();

        if (habitService.changeHabit(user.getEmail(), oldHabit, name, description, frequency)) {
            screen.printEditMessage();
        }

        showHabits(user);
    }

    public void deleteHabit(User user, Habit habit) {
        if (habitService.deleteHabit(user.getEmail(), habit)) {
            screenContainer.getScreen(ScreenContainer.HABIT_DELETE_SCREEN).show();
        }

        showHabits(user);
    }
}
