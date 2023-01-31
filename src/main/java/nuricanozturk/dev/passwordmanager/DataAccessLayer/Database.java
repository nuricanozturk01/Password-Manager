package nuricanozturk.dev.passwordmanager.DataAccessLayer;

import nuricanozturk.dev.passwordmanager.Entity.Application;
import nuricanozturk.dev.passwordmanager.Entity.Log;
import nuricanozturk.dev.passwordmanager.Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.spi.ServiceException;

import javax.persistence.NoResultException;
import java.util.List;

@SuppressWarnings("all")
public class Database
{
    public static SessionFactory factory;

    static
    {
        try
        {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Log.class)
                    .addAnnotatedClass(Application.class)
                    .configure()
                    .buildSessionFactory();
        }
        catch (ServiceException ignored)
        {

        }
    }

    public static Session getSession()
    {
        return factory.getCurrentSession();
    }

    public static void add(Object obj)
    {
        Session session = getSession();
        session.beginTransaction();
        session.persist(obj);
        session.getTransaction().commit();
        session.close();
    }

    public static void remove(Object obj)
    {
        Session session = getSession();
        session.beginTransaction();
        session.remove(obj);
        session.getTransaction().commit();
        session.close();
    }

    public static void update(Object obj)
    {
        Session session = getSession();
        try {
            session.beginTransaction();
            session.update(obj);
            session.getTransaction().commit();
        }
        finally {
            session.close();
        }
    }
    public static User getUserByUsername(String username)
    {
        var session = getSession();

        try {

            session.beginTransaction();

            Query<User> advanceAmount = session.createSQLQuery("select * from user where username=:uname")
                    .addEntity(User.class)
                    .setParameter("uname", username);

            return advanceAmount.getSingleResult();
        }
        catch (NoResultException ignored){
            return null;
        }
        finally {
            session.close();
        }
    }
    public static User getUserByEmail(String email)
    {
        var session = getSession();

        try {

            session.beginTransaction();

            Query<User> advanceAmount = session.createSQLQuery("select * from user where email=:u_email")
                    .addEntity(User.class)
                    .setParameter("u_email", email);

            return advanceAmount.getSingleResult();
        }
        catch (NoResultException ignored){
            return null;
        }
        finally {
            session.close();
        }
    }

    public static List<Application> getApplications(int userId)
    {
        var session = getSession();
        try {

            session.beginTransaction();

            Query<Application> advanceAmount = session.createSQLQuery("select * from application where user_id=:uid")
                    .addEntity(Application.class)
                    .setParameter("uid", userId);

            return advanceAmount.getResultList();
        }
        catch (NoResultException ignored){
            return null;
        }
        finally {
            session.close();
        }
    }

    public static List<Log> getLogs(int userId)
    {
        var session = getSession();
        try {

            session.beginTransaction();

            Query<Log> logs = session.createSQLQuery("select * from log where user_id=:uid")
                    .addEntity(Log.class)
                    .setParameter("uid", userId);

            return logs.getResultList();
        }
        catch (NoResultException ignored){
            return null;
        }
        finally {
            session.close();
        }
    }

    public static String searchEmail(String email) {
        var session = getSession();
        try {

            session.beginTransaction();

            Query<String> logs = session.createSQLQuery("select email from user where email=:u_email")
                    .setParameter("u_email", email);

            return logs.getSingleResult();
        }
        catch (NoResultException ignored){
            return null;
        }
        finally {
            session.close();
        }
    }
}
