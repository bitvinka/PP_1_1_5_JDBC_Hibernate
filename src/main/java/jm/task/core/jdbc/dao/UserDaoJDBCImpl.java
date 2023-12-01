package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }
    @Override
    public void createUsersTable() {
        try {

            try (Statement statement = connection.createStatement()) {
                statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age INT  )");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while creating the table" + e.getMessage());
        }
        System.out.println("DB created successful by JDBC");
    }

    @Override
    public void dropUsersTable() {
        try {

            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DROP TABLE IF EXISTS users");
            }

        } catch (SQLException e) {
            System.out.println("An error occurred while drop the table" + e.getMessage());
        }
        System.out.println("DB drop successful by JDBC");
    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while save User" + e.getMessage());
        }
        System.out.println("DB save User successful by JDBC");
    }

    @Override
    public void removeUserById(long id) {
        try {
            try (PreparedStatement preparedStatement =
                         connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while remove User By Id" + e.getMessage());
        }
        System.out.println("DB remove User By Id successful by JDBC");
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    allUsers.add(user);
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while getAllUsers" + e.getMessage());
        }
        System.out.println("DB getAllUsers successful by JDBC");
        return allUsers;
    }
    @Override
    public void cleanUsersTable() {
        try {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM users");
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while clean Users Table" + e.getMessage());
        }
        System.out.println("DB clean Users Table successful by JDBC");
    }
}
