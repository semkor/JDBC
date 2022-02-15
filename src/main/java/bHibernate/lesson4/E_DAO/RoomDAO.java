package bHibernate.lesson4.E_DAO;

import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.B_model.Room;
import bHibernate.lesson4.B_model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import javax.persistence.NoResultException;
import java.util.List;

public class RoomDAO {
    private static SessionFactory sessionFactory = UserDAO.createSessionFactory();
    private static Session session = null;
    private static Transaction tr = null;

    public static Room save(Room room) {
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
            session.save(room);
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
        return room;
    }

    public static Room update(Room room) {
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(room);
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
        return room;
    }

    public static List<Room> findByParam(int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, String country, String city) {
        List<Room> room = null;
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
                room = sqlQueri(numberOfGuests, price, breakfastIncluded, petsAllowed, country, city);
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
        return room;
    }

    public static Room findById(long id){
        Room room=null;
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
            room = (Room) session.load(Room.class,id);
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
        return room;
    }

    public static void delete(long id){
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
            Room room = session.get(Room.class,id);
            if (room != null)
                session.delete(room);
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

    private static List<Room> sqlQueri(int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, String country, String city) {
        List<Room> room = null;
        NativeQuery nq = session.createNativeQuery(
                "SELECT * FROM ROOMFPR JOIN HOTELFPR ON ROOMFPR.HOTELFPR_ID = HOTELFPR.ID\n" +
                        "WHERE ROOMFPR.NUMBER_OF_GUESTES = :param1 AND ROOMFPR.PRICE = :param2\n" +
                        "      AND ROOMFPR.BREKFAST_INCLUDED = :param3 AND ROOMFPR.PETS_ALLOWED = :param4\n" +
                        "      AND HOTELFPR.COUNTRY = :param5 AND HOTELFPR.CITY = :param6", Room.class);
        nq.setParameter("param1", numberOfGuests);
        nq.setParameter("param2", price);
        nq.setParameter("param3", breakfastIncluded);
        nq.setParameter("param4", petsAllowed);
        nq.setParameter("param5", country);
        nq.setParameter("param6", city);
        room = nq.getResultList();
    return room;
    }
}


