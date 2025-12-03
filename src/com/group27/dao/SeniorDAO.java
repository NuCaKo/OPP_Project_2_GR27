package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Contact;
import java.sql.*;

public class SeniorDAO extends JuniorDAO {

    public boolean addContact(Contact contact) {
        String sql = "INSERT INTO contacts (first_name, last_name, phone_primary, email, birth_date, nickname, linkedin_url, created_at) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getPhonePrimary());
            stmt.setString(4, contact.getEmail());
            stmt.setDate(5, contact.getBirthDate());
            stmt.setString(6, contact.getNickname());
            stmt.setString(7, contact.getLinkedinUrl());

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Database Error (Add): " + e.getMessage());
            return false;
        }
    }

    public boolean deleteContact(int contactId) {
        String sql = "DELETE FROM contacts WHERE contact_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contactId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("Database Error (Delete): " + e.getMessage());
            return false;
        }
    }
}