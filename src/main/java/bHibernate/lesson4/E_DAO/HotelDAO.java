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

    public static Hotel findByFourParam(String name, String country, String city, String street){
        Hotel hotel=null;
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
                hotel = sqlQueri(name, country, city, street);
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

//    public static void delete(long id){
//        try {
//            session = sessionFactory.openSession();
//            tr = session.getTransaction();
//            tr.begin();
////                Hotel hotel = new Hotel();
////                    hotel.setId(id);
////                session.delete(hotel);
//                Hotel hotel = session.load(Hotel.class,50);
//                if (hotel != null) {
//                    session.delete(hotel);
//                }
//            tr.commit();
//            System.out.println("Delete is done");
//        } catch (HibernateException e) {
//            System.err.println("Delete is failed");
//            System.out.println(e.getMessage());
//            if (tr != null)
//                tr.rollback();
//        } finally {
//            if (session != null)
//                session.close();
//        }
//    }

//-------------------------------------------------------------------------------------------------------------------
    private static Hotel sqlQueri(String name, String country, String city, String street) {
        NativeQuery nq = session.createNativeQuery(
                "SELECT * FROM HOTELFPR WHERE BINARY " +
                        "NAME = :nameParam AND BINARY COUNTRY = :countryParam " +
                        "AND BINARY CITY = :cityParam AND BINARY STREET = :streetParam ", Hotel.class);
        nq.setParameter("nameParam", name);
        nq.setParameter("countryParam", country);
        nq.setParameter("cityParam", city);
        nq.setParameter("streetParam", street);
        Hotel hotel = (Hotel) nq.getSingleResult();
        return hotel;
    }
}
