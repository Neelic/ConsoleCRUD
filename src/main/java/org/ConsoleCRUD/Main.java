package org.ConsoleCRUD;

import org.ConsoleCRUD.repository.UserRepository;
import org.ConsoleCRUD.service.UserService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите 1, чтобы войти\nВведите 2, чтобы зарегестрироваться");

        UserService userService = new UserService(new UserRepository());
        int choice = -1;

        do {
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введите число");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.println("email:");
                    String email = scanner.next();
                    System.out.println("password:");
                    String password = scanner.next();

                    userService.loginUser(email, password);
                }
                case 2 -> {
                    System.out.println("email:");
                    String email = scanner.next();
                    System.out.println("password:");
                    String password = scanner.next();

                    userService.registerUser(email, password);
                }
                case 3 -> {
                    System.out.println("email:");
                    String email = scanner.next();
                    userService.deleteUser(email);
                }
                case 4 -> {
                    System.out.println("email:");
                    String email = scanner.next();
                    System.out.println("password:");
                    String password = scanner.next();
                    userService.updateUser(email, password);
                }
                default -> System.out.println("Комманда не найдена");
            }
        } while (choice != 0);
    }
}
