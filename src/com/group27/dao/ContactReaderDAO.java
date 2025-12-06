package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * To read contact information from the database, use the Data Access Object.
 */
public class ContactReaderDAO extends BaseDAO {


    /**
     * Retrieves all non-deleted contacts from the database.
     *
     * @return a list of all contacts
     */
    public List<Contact> getAllContacts() {

        String sql = "SELECT * FROM contacts WHERE is_deleted = 0";
        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return mapResultSetToContacts(rs);
        } catch (SQLException e) {
            e.printStackTrace(); return new ArrayList<>();
        }
    }


    /**
     * Finds contacts where the keyword appears in the designated field.
     *
     * @param fieldName the database column name to search
     * @param keyword   the keyword to search for
     * @return a list of matching contacts
     */
    public List<Contact> searchContacts(String fieldName, String keyword) {
        if (!isValidColumn(fieldName)) return new ArrayList<>();


        String sql = "SELECT * FROM contacts WHERE is_deleted = 0 AND " + fieldName + " LIKE ?";

        if (fieldName.equals("phone_primary")) {

            sql = "SELECT * FROM contacts WHERE is_deleted = 0 AND REPLACE(phone_primary, '-', '') LIKE ?";
            keyword = keyword.replace("-", "").replace(" ", "");
        }

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            return mapResultSetToContacts(rs);
        } catch (SQLException e) {
            e.printStackTrace(); return new ArrayList<>();
        }
    }


    /**
     * Carries out a sophisticated search using several optional criteria.
     *
     * @param firstName       the first name to filter by
     * @param lastName        the last name to filter by
     * @param phone           the phone number to filter by
     * @param email           the email to filter by
     * @param birthMonth      the birth month to filter by
     * @param birthYear       the birth year to filter by
     * @param nickname        the nickname to filter by
     * @param linkedinKeyword the LinkedIn keyword to filter by
     * @return a list of matching contacts
     */
    public List<Contact> searchComplex(String firstName, String lastName, String phone, String email,
                                       String birthMonth, String birthYear, String nickname, String linkedinKeyword) {


        StringBuilder sql = new StringBuilder("SELECT * FROM contacts WHERE is_deleted = 0 ");
        List<Object> params = new ArrayList<>();

        if (firstName != null && !firstName.isEmpty()) { sql.append("AND first_name LIKE ? "); params.add("%" + firstName + "%"); }
        if (lastName != null && !lastName.isEmpty()) { sql.append("AND last_name LIKE ? "); params.add("%" + lastName + "%"); }
        if (phone != null && !phone.isEmpty()) {
            sql.append("AND REPLACE(phone_primary, '-', '') LIKE ? ");
            params.add("%" + phone.replace("-", "").replace(" ", "") + "%");
        }
        if (email != null && !email.isEmpty()) { sql.append("AND email LIKE ? "); params.add("%" + email + "%"); }
        if (nickname != null && !nickname.isEmpty()) { sql.append("AND nickname LIKE ? "); params.add("%" + nickname + "%"); }
        if (linkedinKeyword != null && !linkedinKeyword.isEmpty()) { sql.append("AND linkedin_url LIKE ? "); params.add("%" + linkedinKeyword + "%"); }
        if (birthMonth != null && !birthMonth.isEmpty()) { sql.append("AND MONTH(birth_date) = ? "); params.add(Integer.parseInt(birthMonth)); }
        if (birthYear != null && !birthYear.isEmpty()) { sql.append("AND YEAR(birth_date) = ? "); params.add(Integer.parseInt(birthYear)); }

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {
            for (int i = 0; i < params.size(); i++) stmt.setObject(i + 1, params.get(i));
            ResultSet rs = stmt.executeQuery();
            return mapResultSetToContacts(rs);
        } catch (SQLException e) {
            e.printStackTrace(); return new ArrayList<>();
        }
    }


    /**
     * Retrieves contacts sorted by a specific field.
     *
     * @param orderByField the field to sort by
     * @param isAscending  true for ascending order, false for descending
     * @return a list of sorted contacts
     */
    public List<Contact> getContactsSorted(String orderByField, boolean isAscending) {
        if (!isValidColumn(orderByField)) return new ArrayList<>();
        String direction = isAscending ? "ASC" : "DESC";


        String sql = "SELECT * FROM contacts WHERE is_deleted = 0 ORDER BY " + orderByField + " " + direction;

        try (Connection conn = DatabaseHelper.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            return mapResultSetToContacts(rs);
        } catch (SQLException e) {
            e.printStackTrace(); return new ArrayList<>();
        }
    }


    /**
     * Searches for contacts with an email address containing the specified domain.
     *
     * @param domain the email domain to search for
     * @return a list of matching contacts
     */
    public List<Contact> searchByEmailDomain(String domain) {

        String sql = "SELECT * FROM contacts WHERE is_deleted = 0 AND email LIKE ?";
        try (Connection conn = DatabaseHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%@" + domain + "%");
            ResultSet rs = stmt.executeQuery(); return mapResultSetToContacts(rs);
        } catch (SQLException e) { e.printStackTrace(); return new ArrayList<>(); }
    }


    /**
     * Looks for contacts who fall into a particular age range.
     *
     * @param min the minimum age
     * @param max the maximum age
     * @return a list of matching contacts
     */
    public List<Contact> searchByAgeRange(int min, int max) {

        String sql = "SELECT * FROM contacts WHERE is_deleted = 0 AND TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN ? AND ?";
        try (Connection conn = DatabaseHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, min); stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery(); return mapResultSetToContacts(rs);
        } catch (SQLException e) { e.printStackTrace(); return new ArrayList<>(); }
    }


    /**
     * Retrieves a contact by its unique ID.
     *
     * @param id the ID of the contact
     * @return the Contact object if found, null otherwise
     */
    public Contact getContactById(int id) {

        String sql = "SELECT * FROM contacts WHERE contact_id = ? AND is_deleted = 0";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            List<Contact> results = mapResultSetToContacts(rs);

            if (!results.isEmpty()) {
                return results.get(0);
            }

        } catch (SQLException e) {
            System.out.println("\u001B[31m❌ Error fetching contact by ID: " + e.getMessage() + "\u001B[0m");
        }
        return null;
    }


    /**
     * Determines whether a particular data value has already been taken by another contact.
     *
     * @param columnName the column name to check
     * @param value      the value to check
     * @param excludeId  the contact ID to exclude from the check
     * @return true if the data is taken, false otherwise
     */
    public boolean isDataTaken(String columnName, String value, int excludeId) {
        String sql = "SELECT COUNT(*) FROM contacts WHERE " + columnName + " = ? AND contact_id != ? AND is_deleted = 0";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);
            stmt.setInt(2, excludeId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}