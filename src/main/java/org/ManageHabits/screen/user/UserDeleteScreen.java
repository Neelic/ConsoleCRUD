package org.ManageHabits.screen.user;

import org.ManageHabits.screen.Screen;

public class UserDeleteScreen implements Screen {

    @Override
    public int show() {
        System.out.println("Вы удалили пользователя");
        return NOT_CHOICE;
    }
}
