package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        User[] users = {
                new User("Adele", "Adkins", (byte) 35),
                new User("Bob", "Dylan", (byte) 82),
                new User("Chad", "Kroeger", (byte) 49),
                new User("Dave", "Grohl", (byte) 54)
        };

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser(users[0].getName(), users[0].getLastName(), users[0].getAge());
        userService.saveUser(users[1].getName(), users[1].getLastName(), users[1].getAge());
        userService.saveUser(users[2].getName(), users[2].getLastName(), users[2].getAge());
        userService.saveUser(users[3].getName(), users[3].getLastName(), users[3].getAge());
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
