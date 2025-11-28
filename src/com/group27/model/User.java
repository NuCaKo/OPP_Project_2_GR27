package com.group27.model;

import java.sql.Timestamp;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private Role role;
    private Timestamp createdAt;

    //BOŞ CONSTR DAO İÇİN VAR
    public User() {}

    // CONSTRUCTOR
    public User(String username, String passwordHash, String firstName, String lastName, Role role) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }


    /**
     * UI ekranlarında "Ad Soyad" şeklinde bütünleşik isim gösterir.
     * Örn: "Ahmet Yılmaz"
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * Hata ayıklarken (System.out.println(user)) nesnenin içini okumanı sağlar.
     * Güvenlik gereği şifreyi yazdırmıyoruz!
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