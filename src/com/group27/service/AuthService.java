package com.group27.service;

import com.group27.dao.UserDAO;
import com.group27.model.User;
import com.group27.util.PasswordUtil;

public class AuthService {
    private UserDAO userDAO;

    public AuthService() {
        this.userDAO = new UserDAO();
    }


    public User login(String username, String rawPassword) {

        User user = userDAO.findByUsername(username);

        if (user == null) {
            System.out.println("Error: User not found!");
            return null;
        }


        String hashedInput = PasswordUtil.hashPassword(rawPassword);

        if (user.getPasswordHash().equals(hashedInput)) {
            System.out.println("Sucsesful login" + user.getFirstName() + " " + user.getLastName());
            return user;
        } else {
            System.out.println("wrong pass");
            return null;
        }
    }
}