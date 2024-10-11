package org.ConsoleCRUD.screen;

import org.ConsoleCRUD.screen.user.UserAuthScreen;
import org.ConsoleCRUD.screen.user.UserDeleteScreen;
import org.ConsoleCRUD.screen.user.UserInitScreen;
import org.ConsoleCRUD.screen.user.UserProfileScreen;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ScreenContainer {

    private final HashMap<String, Screen> screens;

    public final static String INIT_SCREEN = "init";
    public final static String AUTH_SCREEN = "auth";
    public final static String DELETE_SCREEN = "delete";
    public final static String PROFILE_USER_SCREEN = "user";

    public ScreenContainer(Scanner scanner) {
        screens = new HashMap<>(
                Map.of(
                        AUTH_SCREEN, new UserAuthScreen(scanner),
                        DELETE_SCREEN, new UserDeleteScreen(),
                        INIT_SCREEN, new UserInitScreen(scanner),
                        PROFILE_USER_SCREEN, new UserProfileScreen(scanner)
                ));
    }

    public Screen getScreen(String name) {
        return screens.getOrDefault(name, null);
    }
}
