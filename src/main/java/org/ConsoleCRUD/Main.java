package org.ConsoleCRUD;

import org.ConsoleCRUD.controller.HabitController;
import org.ConsoleCRUD.controller.UserController;
import org.ConsoleCRUD.repository.HabitRepository;
import org.ConsoleCRUD.repository.UserRepository;
import org.ConsoleCRUD.screen.ScreenContainer;
import org.ConsoleCRUD.service.HabitService;
import org.ConsoleCRUD.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(new UserRepository());
        ScreenContainer screenContainer = new ScreenContainer(scanner);
        HabitService habitService = new HabitService(new HabitRepository());

        UserController userController = new UserController(
                userService,
                screenContainer,
                new HabitController(
                        habitService,
                        screenContainer
                )
        );

        userController.startBeforeAuth();
    }
}
