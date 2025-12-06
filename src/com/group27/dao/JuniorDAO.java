package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Contact;
import java.sql.*;

/**
 * Data Access Object for Junior Developer role, extending ContactReaderDAO.
 * Provides functionality to update contacts.
 */
public class JuniorDAO extends ContactReaderDAO {

    /**
     * Updates an existing contact in the database.
     *
     * @param contact the contact object with updated information
     * @return true if the update was successful, false otherwise
     */
    public boolean updateContact(Contact contact) {

        if (isDataTaken("email", contact.getEmail(), contact.getContactId())) {
            System.out.println("\u001B[31mERROR: This Email is already used by another contact!\u001B[0m");
            return false;
        }

        if (isDataTaken("phone_primary", contact.getPhonePrimary(), contact.getContactId())) {
            System.out.println("\u001B[31mERROR: This Phone Number is already used by another contact!\u001B[0m");
            return false;
        }

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
            stmt.setInt(8, contact.getContactId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}