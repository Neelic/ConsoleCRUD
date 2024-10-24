package org.ManageHabits.screen.habit;

import org.ManageHabits.screen.Screen;
import org.ManageHabits.screen.ScreenUtils;

import java.util.Scanner;

public class HabitScreen implements Screen {

    public final static int EXIT_CHOICE = 0;
    public final static int CHANGE_HABIT_CHOICE = 1;
    public final static int DELETE_HABIT_CHOICE = 2;
    public final static int CHANGE_MARK_CHOICE = 3;
    public final static String FIRST_MESSAGE = String.format("""
            Нажмите
            %d, чтобы вернуться
            %d, чтобы изменить привычку
            %d, чтобы удалить привычку
            %d, чтобы изменить отметку привычки
            """, EXIT_CHOICE, CHANGE_HABIT_CHOICE, DELETE_HABIT_CHOICE, CHANGE_MARK_CHOICE);
    private final Scanner scanner;

    public HabitScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int show() {
        System.out.println(FIRST_MESSAGE);
        return ScreenUtils.getUserChoice(scanner, this::isNotCorrectChoice);
    }

    private boolean isNotCorrectChoice(int choice) {
        return choice != EXIT_CHOICE && choice != CHANGE_HABIT_CHOICE && choice != DELETE_HABIT_CHOICE
                && choice != CHANGE_MARK_CHOICE;
    }
}
