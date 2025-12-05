package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Role;
import com.group27.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

    public boolean addUser(User user) {
        String sql = "INSERT INTO users (username, password_hash, first_name, last_name, role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPasswordHash());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.setString(5, user.getRole().name());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateUser(User user) {
        String sql = "UPDATE users SET username=?, first_name=?, last_name=?, role=? WHERE user_id=?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            ps.setString(4, user.getRole().name());
            ps.setInt(5, user.getUserId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                User u = new User();
                u.setUserId(rs.getInt("user_id"));
                u.setUsername(rs.getString("username"));
                u.setPasswordHash(rs.getString("password_hash"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setRole(Role.valueOf(rs.getString("role")));
                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UNDO için ek metotlar

    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setUserId(rs.getInt("user_id"));
                    u.setUsername(rs.getString("username"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setFirstName(rs.getString("first_name"));
                    u.setLastName(rs.getString("last_name"));
                    u.setRole(Role.valueOf(rs.getString("role")));
                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ? ORDER BY user_id DESC LIMIT 1";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setUserId(rs.getInt("user_id"));
                    u.setUsername(rs.getString("username"));
                    u.setPasswordHash(rs.getString("password_hash"));
                    u.setFirstName(rs.getString("first_name"));
                    u.setLastName(rs.getString("last_name"));
                    u.setRole(Role.valueOf(rs.getString("role")));
                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public java.util.Map<String, String> getContactStatistics() {
        java.util.Map<String, String> stats = new java.util.LinkedHashMap<>();

        try (Connection conn = DatabaseHelper.getConnection(); Statement stmt = conn.createStatement()) {

            // 1. Total Contacts
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM contacts")) {
                if (rs.next()) stats.put("Total Contacts", String.valueOf(rs.getInt(1)));
            }

            // 2. LinkedIn Usage
            try (ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM contacts WHERE linkedin_url IS NOT NULL AND linkedin_url != ''")) {
                if (rs.next()) {
                    int hasLinkedIn = rs.getInt(1);
                    int total = Integer.parseInt(stats.getOrDefault("Total Contacts", "0"));
                    stats.put("With LinkedIn", hasLinkedIn + " (" + (total > 0 ? (hasLinkedIn * 100 / total) : 0) + "%)");
                    stats.put("Without LinkedIn", (total - hasLinkedIn) + "");
                }
            }

            // 3. Average Age
            try (ResultSet rs = stmt.executeQuery("SELECT AVG(TIMESTAMPDIFF(YEAR, birth_date, CURDATE())) FROM contacts")) {
                if (rs.next()) stats.put("Average Age", String.format("%.2f", rs.getDouble(1)));
            }

            // 4. Youngest Contact
            try (ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, birth_date FROM contacts ORDER BY birth_date DESC LIMIT 1")) {
                if (rs.next()) {
                    stats.put("Youngest Contact", rs.getString("first_name") + " " + rs.getString("last_name") + " (" + rs.getDate("birth_date") + ")");
                }
            }

            // 5. Oldest Contact
            try (ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, birth_date FROM contacts ORDER BY birth_date ASC LIMIT 1")) {
                if (rs.next()) {
                    stats.put("Oldest Contact", rs.getString("first_name") + " " + rs.getString("last_name") + " (" + rs.getDate("birth_date") + ")");
                }
            }

            // 6. Most Common First Name
            try (ResultSet rs = stmt.executeQuery("SELECT first_name, COUNT(*) as cnt FROM contacts GROUP BY first_name ORDER BY cnt DESC LIMIT 1")) {
                if (rs.next()) {
                    stats.put("Most Common First Name", rs.getString("first_name") + " (Count: " + rs.getInt("cnt") + ")");
                }
            }
            
             // 7. Most Common Last Name
            try (ResultSet rs = stmt.executeQuery("SELECT last_name, COUNT(*) as cnt FROM contacts GROUP BY last_name ORDER BY cnt DESC LIMIT 1")) {
                if (rs.next()) {
                    stats.put("Most Common Last Name", rs.getString("last_name") + " (Count: " + rs.getInt("cnt") + ")");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            stats.put("Error", e.getMessage());
        }

        return stats;
    }
}
