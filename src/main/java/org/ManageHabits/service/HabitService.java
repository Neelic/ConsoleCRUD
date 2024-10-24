package org.ManageHabits.service;

import org.ManageHabits.repository.HabitRepository;
import org.ManageHabits.repository.dto.HabitDTO;
import org.ManageHabits.repository.dto.UserDTO;
import org.ManageHabits.repository.entity.Habit;
import org.ManageHabits.repository.entity.User;
import org.ManageHabits.repository.mappers.HabitMapper;
import org.ManageHabits.repository.mappers.UserMapper;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class HabitService {

    private final HabitRepository habitRepository;

    public HabitService(HabitRepository habitRepository) {
        this.habitRepository = habitRepository;
    }

    public static int showStatisticOfHabit(HabitDTO habit) {
        List<LocalDate> historyChecks = habit.getHistoryChecks();
        long period = 0;
        switch (habit.getFrequency()) {
            case DAILY -> period = ChronoUnit.DAYS.between(habit.getCreated(), LocalDate.now());
            case WEEKLY -> period = ChronoUnit.WEEKS.between(habit.getCreated(), LocalDate.now());
            case MONTHLY -> period = ChronoUnit.MONTHS.between(habit.getCreated(), LocalDate.now());
        }

        return (int) (historyChecks.size() / (period + 1));
    }

    public static int showStreakOfHabit(HabitDTO habit) {
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

    public void createHabit(UserDTO user, HabitDTO dto) {

        if (dto.getName() == null || dto.getDescription() == null || dto.getFrequency() == null) {
            throw new IllegalArgumentException("Name, description and frequency cannot be null");
        }

        if (dto.getName().isEmpty() || dto.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Name and description cannot be empty");
        }

        if (user.getId() == 0) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Habit habit = HabitMapper.INSTANCE.toEntity(dto);
        User userEntity = UserMapper.INSTANCE.toEntity(user);

        habitRepository.createHabit(userEntity, habit);
    }

    public List<HabitDTO> getHabits(UserDTO user) {
        List<HabitDTO> habits = new ArrayList<>();
        List<Habit> habitsEntity = habitRepository.getHabits(UserMapper.INSTANCE.toEntity(user));
        habitsEntity.forEach(habit -> habits.add(HabitMapper.INSTANCE.toDTO(habit)));

        return habits;
    }

    public void changeHabit(UserDTO user, HabitDTO newHabit) {
        if (newHabit.getName() == null || newHabit.getDescription() == null || newHabit.getFrequency() == null || newHabit.getId() == 0) {
            throw new IllegalArgumentException("Name, description and frequency cannot be null");
        }

        if (newHabit.getName().isEmpty() || newHabit.getDescription().isEmpty()) {
            throw new IllegalArgumentException("Name and description cannot be empty");
        }

        if (user.getId() == 0) {
            throw new IllegalArgumentException("User cannot be null");
        }

        Habit newHabitEntity = HabitMapper.INSTANCE.toEntity(newHabit);
        User userEntity = UserMapper.INSTANCE.toEntity(user);
        habitRepository.changeHabit(userEntity, newHabitEntity);
    }

    public void checkHabit(UserDTO user, HabitDTO habit) {
        if (habit.isCompleted()) {
            return;
        }

        habit.setCompleted(true);
        habit.getHistoryChecks().add(LocalDate.now());

        changeHabit(user, habit);
    }

    public void deleteHabit(UserDTO user, HabitDTO habit) {
        if (habit.getId() == 0) {
            throw new IllegalArgumentException("Habit cannot be null");
        }

        if (user.getId() == 0) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (!habitRepository.getHabits(UserMapper.INSTANCE.toEntity(user)).contains(HabitMapper.INSTANCE.toEntity(habit))) {
            throw new IllegalArgumentException("Habit does not exist");
        }

        habitRepository.deleteHabit(HabitMapper.INSTANCE.toEntity(habit));
    }
}
