package com.group27.ui;

import com.group27.model.Role;
import com.group27.model.User;

public class MenuSelector {

    public static Menu getMenu(User user) {

        /*
        if (user.getRole() == Role.MANAGER) {
            return new ManagerMenu(user);
        } else if (user.getRole() == Role.SENIOR_DEV) {
            return new SeniorDevMenu(user);
        } else if (user.getRole() == Role.JUNIOR_DEV) {
            return new JuniorDevMenu(user);
        }
        */

    //sadece tester dönüyor menüleri ekledikçe güncellemeniz lazım
        return new TesterMenu(user);
    }
}