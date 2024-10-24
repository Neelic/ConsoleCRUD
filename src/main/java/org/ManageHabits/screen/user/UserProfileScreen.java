package org.ManageHabits.screen.user;

import org.ManageHabits.screen.Screen;
import org.ManageHabits.screen.ScreenUtils;

import java.util.Scanner;

public class UserProfileScreen implements Screen {

    public final static int EXIT_PROGRAM_CHOICE = 0;
    public final static int EXIT_PROFILE_CHOICE = 1;
    public final static int CREATE_HABIT_CHOICE = 2;
    public final static int SHOW_HABIT_CHOICE = 3;
    public final static int SHOW_STAT_CHOICE = 4;
    public final static int CHANGE_PROFILE_CHOICE = 5;
    public final static int DELETE_PROFILE_CHOICE = 6;

    public static final String FIRST_SCREEN_MESSAGE = String.format("""
                    Введите:
                    %d, чтобы выйти из программы
                    %d, чтобы выйти из профиля
                    %d, чтобы создать привычку
                    %d, чтобы посмотреть привычки
                    %d, чтобы посмотреть статистику
                    %d, чтобы изменить email, пароль и имя
                    %d, чтобы удалить текущий профиль
                    """, EXIT_PROGRAM_CHOICE, EXIT_PROFILE_CHOICE, CREATE_HABIT_CHOICE, SHOW_HABIT_CHOICE,
            SHOW_STAT_CHOICE, CHANGE_PROFILE_CHOICE, DELETE_PROFILE_CHOICE);

    private final Scanner scanner;

    public UserProfileScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int show() {
        System.out.println(FIRST_SCREEN_MESSAGE);
        return ScreenUtils.getUserChoice(scanner, this::isNotCorrectChoice);
    }

    private boolean isNotCorrectChoice(int choice) {
        return choice != EXIT_PROGRAM_CHOICE && choice != EXIT_PROFILE_CHOICE && choice != CREATE_HABIT_CHOICE
                && choice != SHOW_HABIT_CHOICE && choice != SHOW_STAT_CHOICE;
    }
}
