package bHibernate.lesson4.E_DAO;

import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.B_model.Room;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import javax.persistence.NoResultException;
import java.util.List;

public class HotelDAO {
    private static SessionFactory sessionFactory = UserDAO.createSessionFactory();
    private static Session session = null;
    private static Transaction tr = null;

    public static Hotel save(Hotel hotel){
        try {
            session = sessionFactory.openSession();
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
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(hotel);
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
        return hotel;
    }

    public static List<Hotel> findByHotel(String param, String sql){
        List<Hotel> hotel=null;
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
                NativeQuery nq = session.createNativeQuery(sql, Hotel.class);
                nq.setParameter("param", param);
                hotel = nq.getResultList();
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
        return hotel;
    }

    public static Hotel findById(long id){
        Hotel hotel=null;
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
            hotel = (Hotel) session.load(Hotel.class,id);
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
        return hotel;
    }

    public static void delete(long id){
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
                Hotel hotel = session.get(Hotel.class,id);
                if (hotel != null)
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
}
