package com.group27.ui;

import com.group27.model.Role;
import com.group27.model.User;

/**
 * The factory class is used to choose the right menu according to the role of the user.
 */
public class MenuSelector {

    /**
     * Returns the appropriate Menu implementation for the given user.
     *
     * @param user the user for whom the menu is selected
     * @return the corresponding Menu instance
     */
    public static Menu getMenu(User user) {

        if (user.getRole() == Role.MANAGER) {
            return new ManagerMenu(user);
        }
        else if (user.getRole() == Role.SENIOR_DEV) {
            return new SeniorMenu(user);
        }
        else if (user.getRole() == Role.JUNIOR_DEV) {
            return new JuniorMenu(user);
        }
        else {
            return new TesterMenu(user);
        }
    }
}