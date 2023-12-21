package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;

import java.sql.*;

public class Main {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pp_1_1_4";
    private static final String USER = "root";
    private static final String PASSWORD = "Ohdude0505Bdhjl11721";

    public static void main(String[] args) {
        User[] users = {
                new User("Adele", "Adkins", (byte) 35),
                new User("Bob", "Dylan", (byte) 82),
                new User("Chad", "Kroeger", (byte) 49),
                new User("Dave", "Grohl", (byte) 54)
        };

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);

            //подготавливаю SQL запросы
            PreparedStatement createUserTable = connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS User (" +
                    "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255), " +
                    "lastName VARCHAR(255)," +
                    "age SMALLINT" +
                    ")");

            PreparedStatement addUser = connection.prepareStatement(
                    "INSERT INTO User (name, lastName, age)\n" +
                    "VALUES (?, ?, ?)");

            PreparedStatement getAllUsers = connection.prepareStatement(
                    "SELECT * FROM User");

            PreparedStatement deleteAllUsers = connection.prepareStatement(
                    "DELETE FROM User");

            PreparedStatement deleteUserTable = connection.prepareStatement(
                    "DROP TABLE IF EXISTS User");

            //выполняю операции
            createUserTable.executeUpdate();

            for (User user : users) {
                addUser.setString(1, user.getName());
                addUser.setString(2, user.getLastName());
                addUser.setByte(3, user.getAge());
                addUser.executeUpdate();
                System.out.printf("User с именем – %s добавлен в базу данных\n", user.getName());
            }

            ResultSet resultSet = getAllUsers.executeQuery();
            while(resultSet.next()){
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                user.setId(resultSet.getLong(1));
                System.out.println(user);
            }

            deleteAllUsers.executeUpdate();

            deleteUserTable.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
