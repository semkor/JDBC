package bHibernate.lesson4.E_DAO;

import bHibernate.lesson4.B_model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import javax.persistence.NoResultException;

public class UserDAO {
    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static Transaction tr = null;

    public static User save(User user){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                    session.save(user);
            tr.commit();
            System.out.println("Save is done");
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    public static User update(User user){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                    session.update(user);
            tr.commit();
            System.out.println("Update is done");
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    public static User findById(long id){
        User user=null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                user = (User) session.load(User.class,id);
            tr.commit();
            System.out.println("Find By ID is done");
        } catch (HibernateException | NoResultException e) {
            System.err.println("Find By ID is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    public static User findById(String name, String password){
        User user=null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                    NativeQuery nq = session.createNativeQuery(
                    "SELECT * FROM USERFPR WHERE BINARY USER_NAME = :nameParam AND BINARY PASSWORD = :passwordParam ", User.class);
                    nq.setParameter("nameParam", name);
                    nq.setParameter("passwordParam", password);
                    user = (User) nq.getSingleResult();
            tr.commit();
            System.out.println("Find By ID is done");
        } catch (HibernateException | NoResultException e) {
            System.err.println("Find By ID is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return user;
    }

    public static void delete(long id){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                User user = new User();
                user.setId(id);
                session.delete(user);
            tr.commit();
            System.out.println("Delete is done");
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }

//---------------------------------------------------------------------------------------------------------------------
    public static SessionFactory createSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}

