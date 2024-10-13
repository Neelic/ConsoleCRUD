package org.ConsoleCRUD.screen.user;

import org.ConsoleCRUD.screen.Screen;

import java.util.Scanner;

public class UserAuthScreen implements Screen {

    private final Scanner scanner;

    private String email = null;
    private String password = null;
    private String name = null;
    private boolean isAuth = false;

    public UserAuthScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int show() {
        System.out.println("email:");
        email = scanner.next();
        System.out.println("password:");
        password = scanner.next();

        if (!isAuth) {
            System.out.println("name:");
            name = scanner.next();
        }

        return NOT_CHOICE;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setIsAuth(boolean isAuth) {
        this.isAuth = isAuth;
    }

    public void printWelcomeMessage() {
        System.out.println("Вы вошли!");
    }

    public void printEditMessage() {
        System.out.println("Вы успешно отредактировали профиль!");
    }
}
