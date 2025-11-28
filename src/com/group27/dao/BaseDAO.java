package com.group27.dao;

import com.group27.model.Contact;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseDAO {


    protected List<Contact> mapResultSetToContacts(ResultSet rs) throws SQLException {
        List<Contact> contacts = new ArrayList<>();
        while (rs.next()) {
            Contact c = new Contact();
            c.setContactId(rs.getInt("contact_id"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
            c.setNickname(rs.getString("nickname"));
            c.setPhonePrimary(rs.getString("phone_primary"));
            c.setEmail(rs.getString("email"));
            c.setLinkedinUrl(rs.getString("linkedin_url"));
            c.setBirthDate(rs.getDate("birth_date"));
            contacts.add(c);
        }
        return contacts;
    }

    protected boolean isValidColumn(String col) {
        if (col == null) return false;
        return col.equals("first_name") || col.equals("last_name") ||
                col.equals("phone_primary") || col.equals("email") ||
                col.equals("birth_date");
    }
}