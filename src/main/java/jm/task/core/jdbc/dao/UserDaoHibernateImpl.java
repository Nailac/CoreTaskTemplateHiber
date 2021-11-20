package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery(
                "CREATE TABLE IF NOT EXISTS users (" +
                    "id bigint AUTO_INCREMENT NOT NULL," +
                    "name varchar(100) NOT NULL," +
                    "last_name varchar(100) NOT NULL," +
                    "age tinyint NOT NULL," +
                    "CONSTRAINT table_name_pk PRIMARY KEY (id)" +
                    ");");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("DROP TABLE IF EXISTS users");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<User> userList = (List<User>) session
                .createQuery("From User")
                .list();

        transaction.commit();
        session.close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("TRUNCATE TABLE users");
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
