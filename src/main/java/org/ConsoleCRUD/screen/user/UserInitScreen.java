package org.ConsoleCRUD.screen.user;

import org.ConsoleCRUD.screen.Screen;
import org.ConsoleCRUD.screen.ScreenUtils;

import java.util.Scanner;

public class UserInitScreen implements Screen {

    public final static int EXIT_CHOICE = 0;
    public final static int AUTH_CHOICE = 1;
    public final static int REGISTER_CHOICE = 2;

    public static final String FIRST_SCREEN_MESSAGE = String.format("""
            Введите:
            %d, чтобы выйти
            %d, чтобы войти
            %d, чтобы зарегестрироваться
            """, EXIT_CHOICE, AUTH_CHOICE, REGISTER_CHOICE);

    private final Scanner scanner;

    public UserInitScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public int show() {
        System.out.println(FIRST_SCREEN_MESSAGE);
        return ScreenUtils.getUserChoice(scanner, this::isNotCorrectChoice);
    }

    private boolean isNotCorrectChoice(int choice) {
        return choice != EXIT_CHOICE && choice != AUTH_CHOICE && choice != REGISTER_CHOICE;
    }
}
