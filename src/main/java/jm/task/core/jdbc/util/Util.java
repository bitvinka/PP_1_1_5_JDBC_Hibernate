package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//класс для подключения базы данных
public class Util {
//JDBC********************************************
    public static Connection getConnection() {

        String dbURL = null;
        String dbUsername = null;
        String dbPassword = null;

        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("src/main/resources/database.properties")) {
            properties.load(fileInputStream);
            dbURL = properties.getProperty("url");
            dbUsername = properties.getProperty("username");
            dbPassword = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        try {
            assert dbURL != null;
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//Hibernate ********************************************

    public static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().addAnnotatedClass(User.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        return sessionFactory;
    }

}
