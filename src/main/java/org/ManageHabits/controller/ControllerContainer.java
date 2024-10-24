package org.ManageHabits.controller;

import org.ManageHabits.repository.HabitRepository;
import org.ManageHabits.repository.UserRepository;
import org.ManageHabits.screen.ScreenContainer;
import org.ManageHabits.service.HabitService;
import org.ManageHabits.service.UserService;

import java.util.Scanner;

public class ControllerContainer {

    private static final UserController USER_CONTROLLER;
    private static final HabitController HABIT_CONTROLLER;

    static {
        ScreenContainer screenContainer = new ScreenContainer(new Scanner(System.in));

        HABIT_CONTROLLER = new HabitController(
                new HabitService(new HabitRepository()),
                screenContainer
        );

        USER_CONTROLLER = new UserController(
                new UserService(new UserRepository())
        );
    }

    public static UserController getUserController() {
        return USER_CONTROLLER;
    }

    public static HabitController getHabitController() {
        return HABIT_CONTROLLER;
    }
}
