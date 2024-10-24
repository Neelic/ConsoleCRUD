package org.ManageHabits.repository;

import org.ManageHabits.db.Storage;
import org.ManageHabits.repository.entity.Frequency;
import org.ManageHabits.repository.entity.Habit;
import org.ManageHabits.repository.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HabitRepository {

    public List<Habit> getHabits(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        List<Habit> result = new ArrayList<>();
        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM protected.habits AS h LEFT JOIN protected.habits_history AS hh ON h.id = hh.habit_id WHERE user_id = ?",
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            ps.setInt(1, user.getId());
            ps.execute();
            HashMap<Integer, ArrayList<LocalDate>> history = new HashMap<>();
            // get history of habits
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                history.putIfAbsent(rs.getInt("id"), new ArrayList<>());
                if (rs.getDate("date_checked") != null) {
                    history.get(rs.getInt("id")).add(rs.getDate("date_checked").toLocalDate());
                }
            }

            rs.beforeFirst();
            while (rs.next()) {
                Habit habit = new Habit();
                habit.setName(rs.getString("name"));
                habit.setDescription(rs.getString("description"));
                habit.setFrequency(Frequency.values()[rs.getInt("frequency_id") - 1]);
                habit.setCreated(rs.getDate("created").toLocalDate());
                habit.setCompleted(rs.getBoolean("completed"));
                habit.setId(rs.getInt("id"));
                habit.setHistoryChecks(history.getOrDefault(rs.getInt("id"), new ArrayList<>()));
                if (!result.contains(habit)) {
                    result.add(habit);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public void createHabit(User user, Habit habit) {
        if (user == null || habit == null) {
            throw new IllegalArgumentException("User and habit cannot be null");
        }

        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO protected.habits (name, description, frequency_id, user_id) VALUES (?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, habit.getName());
            ps.setString(2, habit.getDescription());
            ps.setInt(3, habit.getFrequency().ordinal() + 1);
            ps.setInt(4, user.getId());
            ps.executeUpdate();

            ps.getGeneratedKeys().next();
            habit.setId(ps.getGeneratedKeys().getInt(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void changeHabit(User user, Habit habit) {
        if (user == null || habit == null) {
            throw new IllegalArgumentException("User and habits cannot be null");
        }

        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE protected.habits SET name = ?, description = ?, frequency_id = ? WHERE id = ?"
            );
            ps.setString(1, habit.getName());
            ps.setString(2, habit.getDescription());
            ps.setInt(3, habit.getFrequency().ordinal() + 1);
            ps.setInt(4, habit.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteHabit(Habit habit) {
        if (habit == null) {
            throw new IllegalArgumentException("User and habit cannot be null");
        }

        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "DELETE FROM protected.habits WHERE id = ?"
            );
            ps.setInt(1, habit.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
