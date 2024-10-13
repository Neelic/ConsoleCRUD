package org.ConsoleCRUD.screen.habit;

import org.ConsoleCRUD.repository.entity.Habit;
import org.ConsoleCRUD.screen.Screen;
import org.ConsoleCRUD.screen.ScreenUtils;

import java.util.List;
import java.util.Scanner;

public class HabitsShowScreen implements Screen {

    public final static int EXIT_CHOICE = 0;
    public final static String FIRST_MESSAGE = String.format("""
            Нажмите %d, чтобы вернуться или номер привычки, чтобы изменить ее или удалить
            """, EXIT_CHOICE);

    private final Scanner scanner;
    private List<Habit> habits;

    public HabitsShowScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int show() {
        System.out.println(FIRST_MESSAGE);

        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);
            System.out.printf(
                    "%d - %s, %s, %s, %b%n",
                    i + 1, habit.getName(), habit.getDescription(), ScreenUtils.frequencyToString(habit.getFrequency()),
                    habit.isCompleted()
            );
        }

        return ScreenUtils.getUserChoice(scanner, this::isNotCorrectChoice);
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    private boolean isNotCorrectChoice(int choice) {
        return choice < EXIT_CHOICE || choice > habits.size();
    }
}
