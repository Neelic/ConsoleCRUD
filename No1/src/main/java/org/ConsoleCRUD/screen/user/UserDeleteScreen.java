package org.ConsoleCRUD.screen.user;

import org.ConsoleCRUD.screen.Screen;

public class UserDeleteScreen implements Screen {

    @Override
    public int show() {
        System.out.println("Вы удалили пользователя");
        return NOT_CHOICE;
    }
}
