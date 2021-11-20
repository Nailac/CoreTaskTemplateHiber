package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final static String USER = "root";
    private final static String PASSWORD = "root";
    private final static String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try{
                Properties settings = new Properties();
                settings.put(Environment.URL, URL);
                settings.put(Environment.USER, USER);
                settings.put(Environment.PASS, PASSWORD);
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                Configuration configuration = new Configuration();
                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }


    public static Connection createConnection(){
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot create connection.", e);
        }
    }
}
