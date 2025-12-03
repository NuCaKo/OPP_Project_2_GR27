package com.group27.ui;

import com.group27.model.Role;
import com.group27.model.User;

public class MenuSelector {

    public static Menu getMenu(User user) {

        // Kullanıcının rolüne göre doğru menü nesnesini döndür

        if (user.getRole() == Role.SENIOR_DEV) {
            return new SeniorMenu(user);
        }
      else if (user.getRole() == Role.JUNIOR_DEV) {
                return new JuniorMenu(user);
            }

        else {
            // TESTER veya tanımlanmamış roller için
            return new TesterMenu(user);
        }
    }
}