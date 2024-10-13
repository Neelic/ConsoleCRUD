package org.ConsoleCRUD.service;

import org.ConsoleCRUD.repository.HabitRepository;
import org.ConsoleCRUD.repository.entity.Frequency;
import org.ConsoleCRUD.repository.entity.Habit;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public static int showStatisticOfHabit(Habit habit) {
        List<LocalDate> historyChecks = habit.getHistoryChecks();
        long period = 0;
        switch (habit.getFrequency()) {
            case DAILY -> period = ChronoUnit.DAYS.between(habit.getCreated(), LocalDate.now());
            case WEEKLY -> period = ChronoUnit.WEEKS.between(habit.getCreated(), LocalDate.now());
            case MONTHLY -> period = ChronoUnit.MONTHS.between(habit.getCreated(), LocalDate.now());
        }

        return (int) (historyChecks.size() / (period + 1));
    }

    public static int showStreakOfHabit(Habit habit) {
        List<LocalDate> historyChecks = habit.getHistoryChecks();
        Period period = switch (habit.getFrequency()) {
            case DAILY -> Period.ofDays(1);
            case WEEKLY -> Period.ofWeeks(1);
            case MONTHLY -> Period.ofMonths(1);
        };

        int streak = 0;
        for (int i = 0; i < historyChecks.size() - 1; i++) {
            if (historyChecks.get(i).plus(period).equals(historyChecks.get(i + 1))) {
                streak++;
            } else {
                streak = 0;
            }
        }

        if (streak == 0 && historyChecks.size() == 1) {
            return 1;
        }

        return streak;
    }

    public boolean createHabit(String email, String name, String description, Frequency frequency) {
        try {
            Habit habit = new Habit(name, description, frequency);
            habitRepository.createHabit(email, habit);

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public List<Habit> getHabits(String email) {
        List<Habit> habits;
        try {
            habits = habitRepository.getHabits(email);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return habits;
    }

    public boolean changeHabit(String email, Habit oldHabit, String name, String description, Frequency frequency) {
        try {
            Habit newHabit = new Habit(name, description, frequency, oldHabit.getCreated(), oldHabit.isCompleted(),
                    oldHabit.getHistoryChecks());
            habitRepository.changeHabit(email, oldHabit, newHabit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public boolean deleteHabit(String email, Habit habit) {
        try {
            habitRepository.deleteHabit(email, habit);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }
}
