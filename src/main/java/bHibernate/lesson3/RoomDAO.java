package bHibernate.lesson3;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

public class RoomDAO {
    private static Session session = null;
    private static Transaction tr = null;


    public static Room save(Room room){
        try {
            session = HotelDAO.createSessionFactory().openSession();
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

    public static Room update(Room room){
        try {
            session = HotelDAO.createSessionFactory().openSession();
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

    public static Room findById(long id){
        Room room=null;
        try {
            session = HotelDAO.createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                NativeQuery nq = session.createNativeQuery(
                        "SELECT * FROM ROOM JOIN HOTELS ON ROOM.HOTELS_ID = HOTELS.ID WHERE ROOM.ID= ?", Room.class);
                nq.setParameter(1, id);
            room = (Room) nq.getSingleResult();
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
        return room;
    }


    public static void delete(long id){
        try {
            session = HotelDAO.createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                Room room = new Room();
                room.setId(id);
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
}
