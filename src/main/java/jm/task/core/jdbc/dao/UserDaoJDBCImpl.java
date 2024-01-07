package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.*;

public class UserDaoJDBCImpl implements UserDao {

    private final String CREATE_USERS_TABLE = "CREATE TABLE IF NOT EXISTS User (" +
            "id BIGINT PRIMARY KEY AUTO_INCREMENT," +
            "name VARCHAR(255), " +
            "lastName VARCHAR(255)," +
            "age SMALLINT" +
            ")";

    private final String DROP_USERS_TABLE = "DROP TABLE IF EXISTS User";

    private final String SAVE_USER = "INSERT INTO User (name, lastName, age)\n" +
            "VALUES (?, ?, ?)";

    private final String REMOVE_USER_BY_ID = "DELETE FROM User WHERE id = ?";

    private final String GET_ALL_USERS = "SELECT * FROM User";

    private final String CLEAN_USERS_TABLE = "DELETE FROM User";

    private Connection connection = Util.connect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USERS_TABLE)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(DROP_USERS_TABLE)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USER)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_USER_BY_ID)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                User user = new User(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                users.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement = connection.prepareStatement(CLEAN_USERS_TABLE)){
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
