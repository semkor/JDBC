package bHibernate.lesson3;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HotelDAO {
    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static Transaction tr = null;


    public static Hotel save(Hotel hotel){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.save(hotel);
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
        return hotel;
    }

    public static Hotel update(Hotel hotel){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(hotel);
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
        return hotel;
    }

    public static Hotel findById(long id){
        Hotel hotel=null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                hotel = (Hotel) session.get(Hotel.class,id);
            tr.commit();
            System.out.println("Find By ID is done");
        } catch (HibernateException e) {
            System.err.println("Find By ID is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return hotel;
    }

    public static void delete(long id){
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Hotel hotel=(Hotel) session.load(Hotel.class,id);
            if(hotel !=null)
                session.delete(hotel);
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
