package com.group27.dao;

import com.group27.config.DatabaseHelper;
import com.group27.model.Contact;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactReaderDAO extends BaseDAO {


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


    public List<Contact> searchByEmailDomain(String domain) {

        String sql = "SELECT * FROM contacts WHERE is_deleted = 0 AND email LIKE ?";
        try (Connection conn = DatabaseHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%@" + domain + "%");
            ResultSet rs = stmt.executeQuery(); return mapResultSetToContacts(rs);
        } catch (SQLException e) { e.printStackTrace(); return new ArrayList<>(); }
    }


    public List<Contact> searchByAgeRange(int min, int max) {

        String sql = "SELECT * FROM contacts WHERE is_deleted = 0 AND TIMESTAMPDIFF(YEAR, birth_date, CURDATE()) BETWEEN ? AND ?";
        try (Connection conn = DatabaseHelper.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, min); stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery(); return mapResultSetToContacts(rs);
        } catch (SQLException e) { e.printStackTrace(); return new ArrayList<>(); }
    }


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
            System.out.println("❌ Error fetching contact by ID: " + e.getMessage());
        }
        return null;
    }


    public boolean isDataTaken(String columnName, String value, int excludeId) {
        String sql = "SELECT COUNT(*) FROM contacts WHERE " + columnName + " = ? AND contact_id != ? AND is_deleted = 0";

        try (Connection conn = DatabaseHelper.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, value);
            stmt.setInt(2, excludeId);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Eğer 0'dan büyükse, kayıt var demektir.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}