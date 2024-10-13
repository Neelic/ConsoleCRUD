package org.ConsoleCRUD.screen.habit;

import org.ConsoleCRUD.repository.entity.Habit;
import org.ConsoleCRUD.screen.Screen;
import org.ConsoleCRUD.service.HabitService;

import java.util.List;

public class HabitsStatScreen implements Screen {

    private List<Habit> habits;

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    @Override
    public int show() {
        System.out.println("Статистика привычек:");
        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);
            System.out.println((i + 1) + " - " + habit.getName() + ", " + habit.getDescription() +
                    ", " + habit.getFrequency() + ", Выполнение: " + HabitService.showStatisticOfHabit(habit) +
                    ", Стрик: " + HabitService.showStreakOfHabit(habit));
        }

        return NOT_CHOICE;
    }
}
