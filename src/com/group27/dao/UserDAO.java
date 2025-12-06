package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Role;
import com.group27.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for database activities pertaining to users.
 */
public class UserDAO {


    /**
     * Finds a user by their username.
     *
     * @param username the username to search for
     * @return the User object if found, null otherwise
     */
    public User findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getInt("user_id"));
                user.setUsername(rs.getString("username"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));



                try {
                    user.setRole(Role.valueOf(rs.getString("role")));
                } catch (IllegalArgumentException e) {
                    System.err.println("\u001B[31mHata: Tanımsız rol -> " + rs.getString("role") + "\u001B[0m");
                }

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Updates the password for a user.
     *
     * @param userId          the ID of the user
     * @param newPasswordHash the new hashed password
     * @return true if the update was successful, false otherwise
     */
    public boolean updatePassword(int userId, String newPasswordHash) {
        String sql = "UPDATE users SET password_hash = ? WHERE user_id = ?";
        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newPasswordHash);
            stmt.setInt(2, userId);

            int affected = stmt.executeUpdate();
            return affected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}