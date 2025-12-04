package com.group27.ui;

import com.group27.model.Role;
import com.group27.model.User;

public class MenuSelector {

    public static Menu getMenu(User user) {

        // Manager en yetkili → önce kontrol edilir
        if (user.getRole() == Role.MANAGER) {
            return new ManagerMenu(user);
        }
        // Senior
        else if (user.getRole() == Role.SENIOR_DEV) {
            return new SeniorMenu(user);
        }
        // Junior
        else if (user.getRole() == Role.JUNIOR_DEV) {
            return new JuniorMenu(user);
        }
        // Tester
        else {
            return new TesterMenu(user);
        }
    }
}
