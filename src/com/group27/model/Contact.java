package com.group27.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Represents a contact in the system.
 */
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

    /**
     * DAO operations' default constructor.
     */
    public Contact() {}

    /**
     * Constructs a new Contact with the specified details.
     *
     * @param firstName    the first name of the contact
     * @param lastName     the last name of the contact
     * @param nickname     the nickname of the contact
     * @param phonePrimary the primary phone number of the contact
     * @param email        the email address of the contact
     * @param linkedinUrl  the LinkedIn URL of the contact
     * @param birthDate    the birth date of the contact
     */
    public Contact(String firstName, String lastName, String nickname, String phonePrimary, String email, String linkedinUrl, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.phonePrimary = phonePrimary;
        this.email = email;
        this.linkedinUrl = linkedinUrl;
        this.birthDate = birthDate;
    }

    /**
     * Gets the contact ID.
     *
     * @return the contact ID
     */
    public int getContactId() { return contactId; }

    /**
     * Sets the contact ID.
     *
     * @param contactId the contact ID to set
     */
    public void setContactId(int contactId) { this.contactId = contactId; }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Sets the first name.
     *
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() { return lastName; }

    /**
     * Sets the last name.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Gets the nickname.
     *
     * @return the nickname
     */
    public String getNickname() { return nickname; }

    /**
     * Sets the nickname.
     *
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) { this.nickname = nickname; }

    /**
     * Gets the primary phone number.
     *
     * @return the primary phone number
     */
    public String getPhonePrimary() { return phonePrimary; }

    /**
     * Sets the primary phone number.
     *
     * @param phonePrimary the primary phone number to set
     */
    public void setPhonePrimary(String phonePrimary) { this.phonePrimary = phonePrimary; }

    /**
     * Gets the email address.
     *
     * @return the email address
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Gets the LinkedIn URL.
     *
     * @return the LinkedIn URL
     */
    public String getLinkedinUrl() { return linkedinUrl; }

    /**
     * Sets the LinkedIn URL.
     *
     * @param linkedinUrl the LinkedIn URL to set
     */
    public void setLinkedinUrl(String linkedinUrl) { this.linkedinUrl = linkedinUrl; }

    /**
     * Gets the birth date.
     *
     * @return the birth date
     */
    public Date getBirthDate() { return birthDate; }

    /**
     * Sets the birth date.
     *
     * @param birthDate the birth date to set
     */
    public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

    /**
     * Gets the timestamp when the contact was created.
     *
     * @return the creation timestamp
     */
    public Timestamp getCreatedAt() { return createdAt; }

    /**
     * Sets the timestamp when the contact was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    /**
     * Gets the timestamp when the contact was last updated.
     *
     * @return the update timestamp
     */
    public Timestamp getUpdatedAt() { return updatedAt; }

    /**
     * Sets the timestamp when the contact was last updated.
     *
     * @param updatedAt the update timestamp to set
     */
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }


    /**
     * Get the contact's complete name.
     *
     * @return the full name (first name + last name)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }


    /**
     * Gets the nickname, or a hyphen if it is null or empty.
     *
     * @return the safe nickname string
     */
    public String getSafeNickname() {
        return (nickname == null || nickname.isEmpty()) ? "-" : nickname;
    }

    /**
     * Gets the LinkedIn URL, or a hyphen if it is null or empty.
     *
     * @return the safe LinkedIn URL string
     */
    public String getSafeLinkedin() {
        return (linkedinUrl == null || linkedinUrl.isEmpty()) ? "-" : linkedinUrl;
    }

    /**
     * Gives back a string that represents the contact.
     *
     * @return a string representation of the contact
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