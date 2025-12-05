package com.group27.ui;

import com.group27.dao.ManagerDAO;
import com.group27.model.Role;
import com.group27.model.User;

import java.util.List;

public class ManagerMenu extends SeniorMenu {

    private final ManagerDAO managerDAO = new ManagerDAO();

    public ManagerMenu(User user) {
        super(user);
    }

    @Override
    public void show() {
        while (true) {
            System.out.println("\n=== MANAGER MENU ===");
            System.out.println("1) Kullanıcı ekle");
            System.out.println("2) Kullanıcı sil");
            System.out.println("3) Kullanıcı güncelle");
            System.out.println("4) Kullanıcıları listele");
            System.out.println("5) İstatistiksel Bilgiler (Contacts Statistical Info)");
            System.out.println("6) Senior (contact) menüsüne geç");
            System.out.println("0) Çıkış");
            System.out.print("Seçim: ");

            String sec = scanner.nextLine();

            switch (sec) {
                case "1" -> addUserFlow();
                case "2" -> deleteUserFlow();
                case "3" -> updateUserFlow();
                case "4" -> listUsersFlow();
                case "5" -> showStatisticsFlow();
                case "6" -> super.show();
                case "0" -> { return; }
                default -> System.out.println("Hatalı seçim!");
            }
        }
    }

    private void showStatisticsFlow() {
        System.out.println("\n--- CONTACTS STATISTICAL INFO ---");
        java.util.Map<String, String> stats = managerDAO.getContactStatistics();

        if (stats.containsKey("Error")) {
            System.out.println("Error fetching statistics: " + stats.get("Error"));
            return;
        }

        for (java.util.Map.Entry<String, String> entry : stats.entrySet()) {
            System.out.printf("%-25s : %s%n", entry.getKey(), entry.getValue());
        }
        System.out.println("---------------------------------");
    }

    private void addUserFlow() {
        System.out.print("Yeni kullanıcı eklemek istiyor musunuz? (E/H): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("E")) {
            System.out.println("İşlem iptal edildi.");
            return;
        }

        System.out.print("Kullanıcı adı: ");
        String username = scanner.nextLine();

        System.out.print("Şifre (hashlenecek değer): ");
        String password = scanner.nextLine();

        System.out.print("İsim: ");
        String first = scanner.nextLine();

        System.out.print("Soyisim: ");
        String last = scanner.nextLine();

        System.out.print("Rol (TESTER/JUNIOR_DEV/SENIOR_DEV/MANAGER): ");
        Role role = Role.valueOf(scanner.nextLine().trim().toUpperCase());

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(password);
        u.setFirstName(first);
        u.setLastName(last);
        u.setRole(role);

        if (managerDAO.addUser(u)) {
            System.out.println("Kullanıcı eklendi.");
        } else {
            System.out.println("Kullanıcı eklenemedi.");
            return;
        }

        System.out.print("İşlemi geri almak ister misiniz? (E/H): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("E")) {
            User added = managerDAO.getUserByUsername(username);
            if (added != null && managerDAO.deleteUser(added.getUserId())) {
                System.out.println("İşlem geri alındı (eklenen kullanıcı silindi).");
            } else {
                System.out.println("Geri alma yapılamadı.");
            }
        }
    }

    private void deleteUserFlow() {
        System.out.print("Kullanıcı silmek istiyor musunuz? (E/H): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("E")) {
            System.out.println("İşlem iptal edildi.");
            return;
        }

        System.out.print("Silinecek kullanıcı ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        User oldUser = managerDAO.getUserById(id);
        if (oldUser == null) {
            System.out.println("Bu ID'ye sahip kullanıcı bulunamadı.");
            return;
        }

        if (managerDAO.deleteUser(id)) {
            System.out.println("Kullanıcı silindi.");
        } else {
            System.out.println("Kullanıcı silinemedi.");
            return;
        }

        System.out.print("İşlemi geri almak ister misiniz? (E/H): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("E")) {
            if (managerDAO.addUser(oldUser)) {
                System.out.println("İşlem geri alındı (kullanıcı yeniden eklendi, ID farklı olabilir).");
            } else {
                System.out.println("Geri alma yapılamadı.");
            }
        }
    }

    private void updateUserFlow() {
        System.out.print("Kullanıcı güncellemek istiyor musunuz? (E/H): ");
        if (!scanner.nextLine().trim().equalsIgnoreCase("E")) {
            System.out.println("İşlem iptal edildi.");
            return;
        }

        System.out.print("Güncellenecek kullanıcı ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        User oldUser = managerDAO.getUserById(id);
        if (oldUser == null) {
            System.out.println("Bu ID'ye sahip kullanıcı bulunamadı.");
            return;
        }

        System.out.print("Yeni kullanıcı adı: ");
        String username = scanner.nextLine();

        System.out.print("Yeni isim: ");
        String first = scanner.nextLine();

        System.out.print("Yeni soyisim: ");
        String last = scanner.nextLine();

        System.out.print("Yeni rol (TESTER/JUNIOR_DEV/SENIOR_DEV/MANAGER): ");
        Role role = Role.valueOf(scanner.nextLine().trim().toUpperCase());

        User u = new User();
        u.setUserId(id);
        u.setUsername(username);
        u.setFirstName(first);
        u.setLastName(last);
        u.setRole(role);
        u.setPasswordHash(oldUser.getPasswordHash()); // şifreyi koru

        if (managerDAO.updateUser(u)) {
            System.out.println("Kullanıcı güncellendi.");
        } else {
            System.out.println("Kullanıcı güncellenemedi.");
            return;
        }

        System.out.print("İşlemi geri almak ister misiniz? (E/H): ");
        if (scanner.nextLine().trim().equalsIgnoreCase("E")) {
            if (managerDAO.updateUser(oldUser)) {
                System.out.println("İşlem geri alındı (eski bilgiler geri yüklendi).");
            } else {
                System.out.println("Geri alma yapılamadı.");
            }
        }
    }

    private void listUsersFlow() {
        List<User> users = managerDAO.getAllUsers();
        System.out.println("Connection established successfully.");
        for (User u : users) {
            System.out.println(
                    u.getUserId() + " | " +
                            u.getUsername() + " | " +
                            u.getFirstName() + " " + u.getLastName() + " | " +
                            u.getRole()
            );
        }
    }
}
