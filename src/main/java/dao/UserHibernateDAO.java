package dao;

import entity.User;
import org.hibernate.*;
import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {
    private SessionFactory sessionFactory;

    public UserHibernateDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<?> getAllUsers() throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userList = null;

        try {
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM User").list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if(session != null) {
                    session.close();
                }
            } catch (Exception e) {
                throw new SQLException();
            }
            return userList;
        }
    }

    @Override
    public User getUser(String login, String password) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userList;
        User user = null;

        try {
            transaction = session.beginTransaction();
            String str = "FROM User WHERE login = :login AND password = :password";
            Query query = session.createQuery(str);
            query.setParameter("login", login);
            query.setParameter("password", password);
            userList = query.list();
            transaction.commit();
            if (!userList.isEmpty()){
                user = userList.get(0);
            }
        }catch (HibernateException e){
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                if(session != null) {
                    session.close();
                }
            } catch (Exception e) {
                throw new SQLException();
            }
            return user;
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            if (user != null) {
                session.save(user);
            }
            transaction.commit();
        }catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                session.close();
            } catch (Exception e) {
                throw new SQLException();
            }
        }
    }

    @Override
    public void deleteUser(Long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Query qDel = session.createQuery("DELETE User WHERE id = :id");
            qDel.setParameter("id", id);
            qDel.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void editUser(User user) throws SQLException {
        Session session = sessionFactory.openSession();
        String queryStr;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            try {
                session.close();
            } catch (HibernateException e) {
                e.printStackTrace();
            }
        }
    }
}
