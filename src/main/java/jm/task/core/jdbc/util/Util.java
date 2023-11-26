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
        public static final String DB_URL = "jdbc:mysql://localhost:3306/";
        public static final String DB_USER_NAME = "root";
        public static final String DB_PASSWORD = "";

        public static Connection getConnection() {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
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
