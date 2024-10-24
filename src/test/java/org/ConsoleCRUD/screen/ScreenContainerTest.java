package org.ConsoleCRUD.screen;

import org.ConsoleCRUD.screen.habit.*;
import org.ConsoleCRUD.screen.user.UserAuthScreen;
import org.ConsoleCRUD.screen.user.UserDeleteScreen;
import org.ConsoleCRUD.screen.user.UserInitScreen;
import org.ConsoleCRUD.screen.user.UserProfileScreen;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScreenContainerTest {

    @Test
    public void testGetScreen() {
        ScreenContainer screenContainer = new ScreenContainer(null);

        assertEquals(screenContainer.getScreen(ScreenContainer.AUTH_SCREEN).getClass(), UserAuthScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.DELETE_SCREEN).getClass(), UserDeleteScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.HABIT_CREATE_SCREEN).getClass(), HabitCreateScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.HABIT_DELETE_SCREEN).getClass(), HabitDeleteScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.HABIT_SCREEN).getClass(), HabitScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.HABITS_SHOW_SCREEN).getClass(), HabitsShowScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.HABITS_STAT_SCREEN).getClass(), HabitsStatScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.INIT_SCREEN).getClass(), UserInitScreen.class);
        assertEquals(screenContainer.getScreen(ScreenContainer.PROFILE_USER_SCREEN).getClass(), UserProfileScreen.class);
        assertNull(screenContainer.getScreen("test"));
    }
}