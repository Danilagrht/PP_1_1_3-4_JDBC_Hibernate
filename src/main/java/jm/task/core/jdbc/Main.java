package jm.task.core.jdbc;

import java.sql.*;

public class Main {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pp_1_1_4";
    private static final String USER = "root";
    private static final String PASSWORD = "Ohdude0505Bdhjl11721";

    public static void main(String[] args) {
        // Устанавливаем соединение с базой данных
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD)) {

            try (Statement statement = connection.createStatement()) {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS User (" +
                        "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                        "name VARCHAR(255), " +
                        "lastName VARCHAR(255)," +
                        "age SMALLINT" +
                        ")";

                statement.execute(createTableSQL);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
