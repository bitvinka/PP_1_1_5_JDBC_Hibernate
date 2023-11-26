package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Olga", "Ivanova", (byte) 19);
        userService.saveUser("Petr", "Kolobkov", (byte) 45);
        userService.saveUser("Svetlana", "Guskova", (byte) 27);
        userService.saveUser("Lada", "Sidorova", (byte) 36);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
