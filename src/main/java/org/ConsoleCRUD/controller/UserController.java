package org.ConsoleCRUD.controller;

import org.ConsoleCRUD.screen.ScreenContainer;
import org.ConsoleCRUD.screen.user.UserAuthScreen;
import org.ConsoleCRUD.screen.user.UserInitScreen;
import org.ConsoleCRUD.screen.user.UserProfileScreen;
import org.ConsoleCRUD.service.UserService;

public class UserController {

    private final UserService userService;
    private final ScreenContainer screenContainer;
    private final HabitController habitController;

    public UserController(UserService userService, ScreenContainer screenContainer, HabitController habitController) {
        this.userService = userService;
        this.screenContainer = screenContainer;
        this.habitController = habitController;
    }

    public void startBeforeAuth() {
        int choice = screenContainer.getScreen(ScreenContainer.INIT_SCREEN).show();

        switch (choice) {
            case UserInitScreen.EXIT_CHOICE -> System.exit(0);
            case UserInitScreen.AUTH_CHOICE -> authUser();
            case UserInitScreen.REGISTER_CHOICE -> registerUser();
        }
    }

    public void profileScreen() {
        int choice = screenContainer.getScreen(ScreenContainer.INIT_SCREEN).show();

        switch (choice) {
            case UserProfileScreen.EXIT_PROGRAM_CHOICE -> System.exit(0);
            case UserProfileScreen.EXIT_PROFILE_CHOICE -> startBeforeAuth();
            case UserProfileScreen.CREATE_HABIT_CHOICE -> createHabit();
            case UserProfileScreen.SHOW_HABIT_CHOICE -> showHabit();
            case UserProfileScreen.CHANGE_HABIT_CHOICE -> changeHabit();
            case UserProfileScreen.DELETE_HABIT_CHOICE -> deleteHabit();
            case UserProfileScreen.SHOW_STAT_CHOICE -> showStat();
            case UserProfileScreen.CHANGE_PROFILE_CHOICE -> updateUser();
            case UserProfileScreen.DELETE_PROFILE_CHOICE -> deleteUser();
        }
    }

    public void showStat() {
    }

    public void deleteHabit() {
        profileScreen();
    }

    public void changeHabit() {
        profileScreen();
    }

    public void showHabit() {
        profileScreen();
    }

    public void createHabit() {
        profileScreen();
    }

    public void authUser() {
        UserAuthScreen screen = (UserAuthScreen) screenContainer.getScreen(ScreenContainer.AUTH_SCREEN);
        screen.show();
        String email = screen.getEmail();
        String password = screen.getPassword();

        if (userService.loginUser(email, password)) {
            screen.printWelcomeMessage();
            profileScreen();
        }
    }

    public void registerUser() {
        UserAuthScreen screen = (UserAuthScreen) screenContainer.getScreen(ScreenContainer.AUTH_SCREEN);
        screen.show();
        String email = screen.getEmail();
        String password = screen.getPassword();
        String name = screen.getName();

        if (userService.registerUser(email, password, name)) {
            screen.printWelcomeMessage();
            profileScreen();
        }
    }

    public void updateUser() {
        UserAuthScreen screen = (UserAuthScreen) screenContainer.getScreen(ScreenContainer.AUTH_SCREEN);
        screen.show();
        String email = screen.getEmail();
        String password = screen.getPassword();
        String name = screen.getName();

        if (userService.updateUser(email, password, name)) {
            screen.printEditMessage();
            profileScreen();
        }
    }

    public void deleteUser() {
        if (userService.deleteUser()) {
            screenContainer.getScreen(ScreenContainer.DELETE_SCREEN).show();
        }

        startBeforeAuth();
    }
}
