package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Role;
import com.group27.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAO {

    // =============================================================
    //               İSTATİSTİK METOTLARI
    // =============================================================

    public int getTotalContacts() {
        String sql = "SELECT COUNT(*) FROM contacts";
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getUpcomingBirthdaysCount() {
        // SQLite: Önümüzdeki 7 gün içindeki doğum günleri
        String sql = "SELECT COUNT(*) FROM contacts WHERE strftime('%m-%d', birth_date) BETWEEN strftime('%m-%d', 'now') AND strftime('%m-%d', 'now', '+7 days')";

        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            // e.printStackTrace(); // Tarih formatı hatası olursa sessizce 0 dön
            return 0;
        }
        return 0;
    }

    public int getNoEmailCount() {
        String sql = "SELECT COUNT(*) FROM contacts WHERE email IS NULL OR email = ''";
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // =============================================================
    //               USER CRUD İŞLEMLERİ
    // =============================================================

    // ----------------------------------------------------
    // ADD NEW USER
    // ----------------------------------------------------
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

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ----------------------------------------------------
    // DELETE USER
    // ----------------------------------------------------
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ----------------------------------------------------
    // UPDATE USER (Şifre Hariç)
    // ManagerMenu sadece isim/rol güncellediği için şifreyi sql'den çıkardım.
    // ----------------------------------------------------
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

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ----------------------------------------------------
    // GET ALL USERS
    // ----------------------------------------------------
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRowToUser(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ----------------------------------------------------
    // GET USER BY ID
    // ----------------------------------------------------
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ----------------------------------------------------
    // GET USER BY USERNAME
    // ----------------------------------------------------
    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ? ORDER BY user_id DESC LIMIT 1";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUser(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // =============================================================
    //               YARDIMCI METOT (Kod tekrarını önler)
    // =============================================================
    private User mapRowToUser(ResultSet rs) throws SQLException {
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