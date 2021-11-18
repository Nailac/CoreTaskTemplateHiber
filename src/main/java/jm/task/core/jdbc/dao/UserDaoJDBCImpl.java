package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.createConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id bigint AUTO_INCREMENT NOT NULL," +
                    "name varchar(100) NOT NULL," +
                    "last_name varchar(100) NOT NULL," +
                    "age tinyint NOT NULL," +
                    "CONSTRAINT table_name_pk PRIMARY KEY (id)" +
                    ");";

            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.createConnection()) {
            String query = "DROP TABLE IF EXISTS users;";
            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.createConnection()) {

            String query = String.format(
                    "INSERT INTO users (name, last_name, age) VALUES ('%s', '%s', %d)",
                    name,
                    lastName,
                    age
            );

            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.createConnection()) {

            String query = String.format(
                    "DELETE FROM users WHERE id = '%d'", id
            );

            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = Util.createConnection()) {
            String query = "SELECT * FROM users";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                long id = resultSet.getByte("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                byte age = resultSet.getByte("age");

                userList.add(new User(name, lastName, age));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.createConnection()) {

            String query = "TRUNCATE users";

            connection.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
