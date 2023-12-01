package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public static final SessionFactory sessionFactory = Util.getSessionFactory();
    private Transaction transaction;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction =  session.beginTransaction();
            session.createNativeQuery(" CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), lastName VARCHAR(100), age INT  )").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction =  session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction =  session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction =  session.beginTransaction();
                 session.remove(session.find(User.class, id));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction =  session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction =  session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
