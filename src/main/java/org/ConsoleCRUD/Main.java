package org.ConsoleCRUD;

import org.ConsoleCRUD.controller.HabitController;
import org.ConsoleCRUD.controller.UserController;
import org.ConsoleCRUD.repository.UserRepository;
import org.ConsoleCRUD.screen.ScreenContainer;
import org.ConsoleCRUD.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService(new UserRepository());

        UserController userController = new UserController(
                userService,
                new ScreenContainer(scanner),
                new HabitController()
        );

        userController.startBeforeAuth();
    }
}
