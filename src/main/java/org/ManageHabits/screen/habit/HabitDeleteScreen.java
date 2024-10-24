package org.ManageHabits.screen.habit;

import org.ManageHabits.screen.Screen;

public class HabitDeleteScreen implements Screen {

    @Override
    public int show() {
        System.out.println("Вы удалили привычку");
        return NOT_CHOICE;
    }
}
