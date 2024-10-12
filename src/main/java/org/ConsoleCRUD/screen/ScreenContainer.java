package org.ConsoleCRUD.screen;

import org.ConsoleCRUD.screen.habit.HabitCreateScreen;
import org.ConsoleCRUD.screen.habit.HabitDeleteScreen;
import org.ConsoleCRUD.screen.habit.HabitScreen;
import org.ConsoleCRUD.screen.habit.HabitsShowScreen;
import org.ConsoleCRUD.screen.user.UserAuthScreen;
import org.ConsoleCRUD.screen.user.UserDeleteScreen;
import org.ConsoleCRUD.screen.user.UserInitScreen;
import org.ConsoleCRUD.screen.user.UserProfileScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScreenContainer {

    public final static String INIT_SCREEN = "init";
    public final static String AUTH_SCREEN = "auth";
    public final static String DELETE_SCREEN = "delete";
    public final static String PROFILE_USER_SCREEN = "user";

    public final static String HABIT_CREATE_SCREEN = "h_create";
    public final static String HABIT_DELETE_SCREEN = "h_delete";
    public final static String HABIT_SCREEN = "h";
    public final static String HABITS_SHOW_SCREEN = "h_show";

    private final HashMap<String, Screen> screens;

    public ScreenContainer(Scanner scanner) {
        screens = new HashMap<>(
                Map.of(
                        AUTH_SCREEN, new UserAuthScreen(scanner),
                        DELETE_SCREEN, new UserDeleteScreen(),
                        INIT_SCREEN, new UserInitScreen(scanner),
                        PROFILE_USER_SCREEN, new UserProfileScreen(scanner),
                        HABIT_CREATE_SCREEN, new HabitCreateScreen(scanner),
                        HABIT_DELETE_SCREEN, new HabitDeleteScreen(),
                        HABIT_SCREEN, new HabitScreen(scanner),
                        HABITS_SHOW_SCREEN, new HabitsShowScreen(scanner)
                ));
    }

    public Screen getScreen(String name) {
        return screens.getOrDefault(name, null);
    }
}
