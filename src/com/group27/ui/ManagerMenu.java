package com.group27.ui;

import com.group27.dao.ManagerDAO;
import com.group27.model.Role;
import com.group27.model.User;
import com.group27.util.InputHelper;

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

            System.out.println("--- Senior Yetkileri ---");
            System.out.println("5) Tüm contactları listele");
            System.out.println("6) Tek alanlı arama");
            System.out.println("7) Çok alanlı arama");
            System.out.println("8) Custom arama");
            System.out.println("9) Contact sıralama");
            System.out.println("10) Contact güncelle");
            System.out.println("11) Contact ekle");
            System.out.println("12) Contact sil");

            System.out.println("0) Çıkış");
            System.out.print("Seçim: ");

            String sec = scanner.nextLine();

            switch (sec) {

                // ---------------- USER CRUD ----------------
                case "1" -> addUserFlow();
                case "2" -> deleteUserFlow();
                case "3" -> updateUserFlow();
                case "4" -> listUsersFlow();

                // ---------------- SENIOR CONTACT OPS ---------------
                case "5" -> super.handleTesterOperations("1");
                case "6" -> super.handleTesterOperations("2");
                case "7" -> super.handleTesterOperations("3");
                case "8" -> super.handleTesterOperations("4");
                case "9" -> super.handleTesterOperations("5");
                case "10" -> super.performUpdate();
                case "11" -> super.performAdd();
                case "12" -> super.performDelete();

                case "0" -> { return; }

                default -> System.out.println("Hatalı seçim!");
            }
        }
    }

    // -------------------------------------------------------
    //                USER CRUD FUNCTIONS
    // -------------------------------------------------------

    private void addUserFlow() {
        System.out.println("\n--- YENİ KULLANICI EKLE ---");

        String username = input.readNickname("Kullanıcı Adı", true);

        System.out.print("Şifre (hashlenecek değer) ");

        String password = input.readPassword("");


        String first = input.readName("İsim", true);
        String last = input.readName("Soyisim", true);


        //switch case e çevirilecek
        System.out.print("Rol (TESTER/JUNIOR_DEV/SENIOR_DEV/MANAGER): ");
        Role role = Role.valueOf(scanner.nextLine().trim().toUpperCase());

        User u = new User();
        u.setUsername(username);
        u.setPasswordHash(password);
        u.setFirstName(first);
        u.setLastName(last);
        u.setRole(role);

        if (managerDAO.addUser(u))
            System.out.println("Kullanıcı başarıyla eklendi.");
        else
            System.out.println("Kullanıcı eklenemedi.");
    }

    private void deleteUserFlow() {
        System.out.println("\n--- KULLANICI SİL ---");
        int id = input.readValidInt("Silinecek Kullanıcı ID");

        if (managerDAO.deleteUser(id))
            System.out.println("Kullanıcı silindi.");
        else
            System.out.println("Silme işlemi başarısız.");
    }

    private void updateUserFlow() {
        System.out.println("\n--- KULLANICI GÜNCELLE ---");

        int id = input.readValidInt("Güncellenecek Kullanıcı ID");

        User old = managerDAO.getUserById(id);
        if (old == null) {
            System.out.println("Kullanıcı bulunamadı.");
            return;
        }

        System.out.print("Yeni Kullanıcı Adı ");
        String username = input.readNickname("", true);


        String first = input.readName("Yeni İsim", true);
        String last = input.readName("Yeni Soyisim", true);


        //swithc case e çevirilecek
        System.out.print("Yeni Rol: ");
        Role role = Role.valueOf(scanner.nextLine().trim().toUpperCase());

        old.setUsername(username);
        old.setFirstName(first);
        old.setLastName(last);
        old.setRole(role);

        if (managerDAO.updateUser(old))
            System.out.println("Kullanıcı güncellendi.");
        else
            System.out.println("Kullanıcı güncellenemedi.");
    }

    private void listUsersFlow() {
        System.out.println("\n--- TÜM KULLANICILAR ---");

        List<User> users = managerDAO.getAllUsers();
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
