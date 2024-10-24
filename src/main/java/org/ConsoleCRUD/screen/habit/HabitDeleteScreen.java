package org.ConsoleCRUD.screen.habit;

import org.ConsoleCRUD.screen.Screen;

public class HabitDeleteScreen implements Screen {

    @Override
    public int show() {
        System.out.println("Вы удалили привычку");
        return NOT_CHOICE;
    }
}
