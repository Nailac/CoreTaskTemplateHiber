package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dima", "Yakubovski" , (byte) 22);
        userService.saveUser("Asya", "Yakubovskaya" , (byte) 22);
        userService.saveUser("Danya", "Tatarinov" , (byte) 23);
        userService.saveUser("Artem", "Dragin" , (byte) 27);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}

