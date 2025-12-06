package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Contact;
import java.sql.*;

/**
 * Data Access Object for Senior Developer role, extending JuniorDAO.
 * Provides functionality to add, delete, and restore contacts.
 */
public class SeniorDAO extends JuniorDAO {


    /**
     * Adds a new contact to the database.
     *
     * @param contact the contact to add
     * @return true if the contact was added successfully, false otherwise
     */
    public boolean addContact(Contact contact) {
        if (isDataTaken("email", contact.getEmail(), -1)) {
            System.out.println("\u001B[31mERROR: A contact with this Email already exists!\u001B[0m");
            return false;
        }

        if (isDataTaken("phone_primary", contact.getPhonePrimary(), -1)) {
            System.out.println("\u001B[31mERROR: A contact with this Phone Number already exists!\u001B[0m");
            return false;
        }

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
            System.err.println("\u001B[31mDatabase Error (Add): " + e.getMessage() + "\u001B[0m");
            return false;
        }
    }

    /**
     * Soft deletes a contact by setting the is_deleted flag to 1.
     *
     * @param contactId the ID of the contact to delete
     * @return true if the deletion was successful, false otherwise
     */
    public boolean deleteContact(int contactId) {
        String sql = "UPDATE contacts SET is_deleted = 1 WHERE contact_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contactId);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("\u001B[31mDatabase Error (Soft Delete): " + e.getMessage() + "\u001B[0m");
            return false;
        }
    }

    /**
     * Restores a soft-deleted contact by setting the is_deleted flag to 0.
     *
     * @param contactId the ID of the contact to restore
     * @return true if the restoration was successful, false otherwise
     */
    public boolean restoreContact(int contactId) {
        String sql = "UPDATE contacts SET is_deleted = 0 WHERE contact_id = ?";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contactId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("\u001B[31mDatabase Error (Restore): " + e.getMessage() + "\u001B[0m");
            return false;
        }
    }
}