package org.ConsoleCRUD.repository;

import org.ConsoleCRUD.db.Storage;
import org.ConsoleCRUD.repository.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public void addUser(User user) {
        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "INSERT INTO protected.users (email, password, name) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS
            );
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public User getUser(String email) {
        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "SELECT * FROM protected.users WHERE email = ?"
            );
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            return new User(
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getInt("id")
            );
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void deleteUser(User user) {
        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement("DELETE FROM protected.users WHERE id = ?");
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void updateUser(User user) {
        try (Connection c = Storage.getInstance().getConnection()) {
            PreparedStatement ps = c.prepareStatement(
                    "UPDATE protected.users SET password = ?, name = ?, email = ? WHERE id = ?"
            );
            ps.setString(1, user.getPassword());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
