package com.group27.model;

import java.sql.Timestamp;

/**
 * Represents a user in the system.
 */
public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private Role role;
    private Timestamp createdAt;

    /**
     * Default constructor for DAO operations.
     */
    public User() {}

    /**
     * Constructs a new User with the specified details.
     *
     * @param username     the username of the user
     * @param passwordHash the hashed password of the user
     * @param firstName    the first name of the user
     * @param lastName     the last name of the user
     * @param role         the role of the user
     */
    public User(String username, String passwordHash, String firstName, String lastName, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public int getUserId() { return userId; }

    /**
     * Sets the user ID.
     *
     * @param userId the user ID to set
     */
    public void setUserId(int userId) { this.userId = userId; }

    /**
     * Gets the username.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) { this.username = username; }

    /**
     * Gets the password hash.
     *
     * @return the password hash
     */
    public String getPasswordHash() { return passwordHash; }

    /**
     * Sets the password hash.
     *
     * @param passwordHash the password hash to set
     */
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

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
     * Gets the user's role.
     *
     * @return the role
     */
    public Role getRole() { return role; }

    /**
     * Sets the user's role.
     *
     * @param role the role to set
     */
    public void setRole(Role role) { this.role = role; }

    /**
     * Gets the timestamp when the user was created.
     *
     * @return the creation timestamp
     */
    public Timestamp getCreatedAt() { return createdAt; }

    /**
     * Sets the timestamp when the user was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


    /**
     * Gets the full name of the user.
     *
     * @return the full name (first name + last name)
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }


    /**
     * Gives back a string that represents the user.
     *
     * @return a string representation of the user
     */
    @Override
    public String toString() {
        return "User{" +
                "ID=" + userId +
                ", Username='" + username + '\'' +
                ", Name='" + getFullName() + '\'' +
                ", Role=" + role +
                '}';
    }
}