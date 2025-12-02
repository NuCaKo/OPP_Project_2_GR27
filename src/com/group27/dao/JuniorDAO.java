package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Contact;
import java.sql.*;

public class JuniorDAO extends ContactReaderDAO {
    
    public boolean updateContact(Contact contact) {
        String sql = "UPDATE contacts SET first_name=?, last_name=?, nickname=?, phone_primary=?, email=?, linkedin_url=?, birth_date=?, updated_at=CURRENT_TIMESTAMP WHERE contact_id=?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, contact.getFirstName());
            stmt.setString(2, contact.getLastName());
            stmt.setString(3, contact.getNickname());
            stmt.setString(4, contact.getPhonePrimary());
            stmt.setString(5, contact.getEmail());
            stmt.setString(6, contact.getLinkedinUrl());
            stmt.setDate(7, contact.getBirthDate());
            stmt.setInt(8, contact.getContactId()); // ID en sonda

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0; // Güncelleme başarılıysa true döner

        } catch (SQLException e) {
            System.err.println("❌ Database Error: " + e.getMessage());
            return false;
        }
    }
}