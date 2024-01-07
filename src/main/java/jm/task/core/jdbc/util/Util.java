package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/pp_1_1_4";
    private static final String USER = "root";
    private static final String PASSWORD = "Ohdude0505Bdhjl11721";

    private static Connection connection;

    public static Connection connect() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
