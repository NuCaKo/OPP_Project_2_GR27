package com.group27.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Contact {
    private int contactId;
    private String firstName;
    private String lastName;
    private String nickname;
    private String phonePrimary;
    private String email;
    private String linkedinUrl;
    private Date birthDate;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // --- CONSTRUCTORS
    public Contact() {}

    public Contact(String firstName, String lastName, String nickname, String phonePrimary, String email, String linkedinUrl, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.phonePrimary = phonePrimary;
        this.email = email;
        this.linkedinUrl = linkedinUrl;
        this.birthDate = birthDate;
    }

    public int getContactId() { return contactId; }
    public void setContactId(int contactId) { this.contactId = contactId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPhonePrimary() { return phonePrimary; }
    public void setPhonePrimary(String phonePrimary) { this.phonePrimary = phonePrimary; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getLinkedinUrl() { return linkedinUrl; }
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }

    public Date getBirthDate() { return birthDate; }
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }



    /**
     * UI için Tam İsim döndürür.
     * @return "Ahmet Yılmaz" formatında string.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * UI'da null değerler yerine "-" basmak için güvenli getter.
     * BaseMenu.java içindeki "if null" kontrollerini azaltır.
     */
    public String getSafeNickname() {
        return (nickname == null || nickname.isEmpty()) ? "-" : nickname;
    }

    public String getSafeLinkedin() {
        return (linkedinUrl == null || linkedinUrl.isEmpty()) ? "-" : linkedinUrl;
    }

    /**
     * Nesneyi konsola yazdırdığında (debug için) anlamlı çıktı verir.
     */
    @Override
    public String toString() {
        return "Contact{" +
                "ID=" + contactId +
                ", Name='" + firstName + " " + lastName + '\'' +
                ", Phone='" + phonePrimary + '\'' +
                ", Email='" + email + '\'' +
                '}';
    }
}