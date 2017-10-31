package edu.matc.persistence;

import edu.matc.entity.UserRole;
import edu.matc.entity.Users;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    private final Logger log = Logger.getLogger(this.getClass());

    /** Return a list of all users
     *
     * @return All users
     */
    public List<Users> getAllUsers() {
        log.info("----- Entered getAllUsers ----");
        List<Users> users = new ArrayList<Users>();
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            users = session.createCriteria(Users.class).list();
        } catch (HibernateException hibernateException) {
            log.error("Error getting all users", hibernateException);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return users;
    }

    /**
     * retrieve a user given their id
     *
     * @param id the user's id
     * @return user
     */
    public Users getUser(int id) {
        Users user = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            user = (Users) session.get(Users.class, id);
        } catch (HibernateException hibernateException) {
            log.error("Error getting user: " + id, hibernateException);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return user;
    }

    /**
     * add a user
     *
     * @param user
     * @return the id of the inserted record
     */
    public String addUser(Users user) {
        String id = null;

        Transaction transaction = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            id = (String) session.save(user);

            transaction.commit();
        } catch (HibernateException hibernateException) {
            log.error("Error occurred attempting to add user: " + user, hibernateException);
            if (transaction != null) {
                try {
                    log.error("Error occurred, attempting to rollback transaction adding user: " + user);
                    transaction.rollback();
                } catch (HibernateException hibernateException2) {
                    log.error("Error occurred during rollback for user: " + user, hibernateException2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return id;
    }

    /**
     * delete a user by id
     * @param id the user's id
     */
    public void deleteUser(int id) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(getUser(id));
            transaction.commit();
        } catch (HibernateException hibernateException) {
            log.error("Error occurred attempting to delete user id: " + id, hibernateException);
            if (transaction != null) {
                try {
                    log.error("Error occurred, attempting to rollback transaction deleting user id: " + id);
                    transaction.rollback();
                } catch (HibernateException hibernateException2) {
                    log.error("Error occurred during rollback deleting userId: " + id, hibernateException2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Update the user
     * @param user
     */
    public void updateUser(Users user) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = SessionFactoryProvider.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(user);
            transaction.commit();
        } catch (HibernateException hibernateException) {
            log.error("Error occurred attempting to update user: " + user, hibernateException);
            if (transaction != null) {
                try {
                    log.error("Error occurred, attempting to rollback transaction update user: " + user);
                    transaction.rollback();
                } catch (HibernateException hibernateException2) {
                    log.error("Error occurred during rollback updating user: " + user, hibernateException2);
                }
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

