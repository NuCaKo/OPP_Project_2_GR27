package com.group27.service;

import com.group27.dao.UserDAO;
import com.group27.model.User;
import com.group27.util.PasswordUtil;

/**
 * Logic for authentication is handled by a service class.
 */
public class AuthService {
    private UserDAO userDAO;

    /**
     * Constructs an AuthService with a new UserDAO.
     */
    public AuthService() {
        this.userDAO = new UserDAO();
    }


    /**
     * Uses the provided username and password to authenticate a user.
     *
     * @param username    the username of the user
     * @param rawPassword the raw password of the user
     * @return the User object if authentication is successful, null otherwise
     */
    public User login(String username, String rawPassword) {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            System.out.println("\u001B[31mError: User not found!\u001B[0m");
            return null;
        }


        String hashedInput = PasswordUtil.hashPassword(rawPassword);

        if (user.getPasswordHash().equals(hashedInput)) {
            System.out.println("Sucsesful login" + user.getFirstName() + " " + user.getLastName());
            return user;
        } else {
            System.out.println("\u001B[31mwrong pass\u001B[0m");
            return null;
        }
    }
}