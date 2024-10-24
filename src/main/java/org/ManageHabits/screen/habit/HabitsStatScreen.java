package org.ManageHabits.screen.habit;

import org.ManageHabits.repository.entity.Habit;
import org.ManageHabits.screen.Screen;
import org.ManageHabits.screen.ScreenUtils;
import org.ManageHabits.service.HabitService;

import java.util.List;

public class HabitsStatScreen implements Screen {

    private List<Habit> habits;

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    @Override
    public int show() {
        System.out.println("Статистика привычек:");

        if (habits == null) {
            return NOT_CHOICE;
        }

        for (int i = 0; i < habits.size(); i++) {
            Habit habit = habits.get(i);
            System.out.println((i + 1) + " - " + habit.getName() + ", " + habit.getDescription() +
                    ", " + ScreenUtils.frequencyToString(habit.getFrequency()) + ", Выполнение: " + HabitService.showStatisticOfHabit(habit) +
                    ", Стрик: " + HabitService.showStreakOfHabit(habit));
        }

        return NOT_CHOICE;
    }
}
