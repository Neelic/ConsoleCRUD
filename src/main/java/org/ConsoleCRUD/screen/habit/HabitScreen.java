package org.ConsoleCRUD.screen.habit;

import org.ConsoleCRUD.screen.Screen;
import org.ConsoleCRUD.screen.ScreenUtils;

import java.util.Scanner;

public class HabitScreen implements Screen {

    public final static int EXIT_CHOICE = 0;
    public final static int CHANGE_HABIT_CHOICE = 1;
    public final static int DELETE_HABIT_CHOICE = 2;
    public final static String FIRST_MESSAGE = String.format("""
            Нажмите
            %d, чтобы вернуться
            %d, чтобы изменить привычку
            %d, чтобы удалить привычку
            """, EXIT_CHOICE, CHANGE_HABIT_CHOICE, DELETE_HABIT_CHOICE);
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
        return choice != EXIT_CHOICE && choice != CHANGE_HABIT_CHOICE && choice != DELETE_HABIT_CHOICE;
    }
}
