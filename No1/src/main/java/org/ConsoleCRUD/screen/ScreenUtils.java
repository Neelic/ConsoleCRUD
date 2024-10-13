package org.ConsoleCRUD.screen;

import org.ConsoleCRUD.repository.entity.Frequency;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public class ScreenUtils {

    public static int getUserChoice(Scanner scanner, Predicate<Integer> isNotCorrectChoice) {
        int choice = Screen.NOT_CHOICE;

        do {
            try {
                choice = scanner.nextInt();

                if (isNotCorrectChoice.test(choice)) {
                    System.out.println("Комманда не найдена");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
                scanner.next();
            }
        } while (isNotCorrectChoice.test(choice));

        return choice;
    }

    public static String frequencyToString(Frequency frequency) {
        return switch (frequency) {
            case DAILY -> "ежедневно";
            case WEEKLY -> "еженедельно";
            case MONTHLY -> "ежемесячно";
        };
    }
}
