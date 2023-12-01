package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();
    @Override
    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
    }
    @Override
    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("Человек с именем – " + name + " добавлен в базу данных");
    }
    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }
    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = userDaoHibernate.getAllUsers();
        for (User user : allUsers) {
            System.out.println(user);
        }
        return allUsers;
    }
    @Override
    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }
}
