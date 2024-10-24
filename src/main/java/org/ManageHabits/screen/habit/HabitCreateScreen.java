package org.ManageHabits.screen.habit;

import org.ManageHabits.repository.entity.Frequency;
import org.ManageHabits.screen.Screen;
import org.ManageHabits.screen.ScreenUtils;

import java.util.Scanner;

public class HabitCreateScreen implements Screen {

    private final Scanner scanner;
    private String name;

    private String description;
    private Frequency frequency;

    public HabitCreateScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int show() {
        System.out.println("Введите имя привычки:");
        do {
            name = scanner.nextLine();
        } while (name.isEmpty());
        System.out.println("Введите описание привычки:");
        description = scanner.nextLine();
        System.out.println("Введите частоту повторений привычки:");
        for (Frequency frequency : Frequency.values()) {
            System.out.println((frequency.ordinal() + 1) + " - " + ScreenUtils.frequencyToString(frequency));
        }

        int choice = ScreenUtils.getUserChoice(scanner,
                frequency -> frequency < 1 || frequency > Frequency.values().length);
        frequency = Frequency.values()[choice - 1];

        return NOT_CHOICE;
    }

    public void printEditMessage() {
        System.out.println("Привычка изменена");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Frequency getFrequency() {
        return frequency;
    }
}
