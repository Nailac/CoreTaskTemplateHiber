package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public final static String user = "root";
    public final static String password = "root";
    public final static String url = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";

    public static Connection createConnection(){
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection.", e);
        }
    }
}
