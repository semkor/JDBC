package bHibernate.lesson4.E_DAO;

import bHibernate.lesson4.B_model.ENUM.UserType;
import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.B_model.Order;
import bHibernate.lesson4.B_model.Room;
import bHibernate.lesson4.B_model.User;
import bHibernate.lesson4.C_controller.OrderController;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.NoResultException;
import java.util.Date;
import java.util.List;

public class OrderDAO {
    private static SessionFactory sessionFactory = UserDAO.createSessionFactory();
    private static Session session = null;
    private static Transaction tr = null;

    public static Order save(User user,Room room,Date dateFrom, Date dateTo, double moneyPaid) {
        Order order = null;
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
                order = creatObject(user,room,dateFrom,dateTo,moneyPaid);
                session.save(order);
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
        return order;
    }

    public static Order update(Order order){
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(order);
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
        return order;
    }

    public static Order findById(long id){
        Order order=null;
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
                order = (Order) session.load(Order.class,id);
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
        return order;
    }

    public static void delete(User user,Room room,Date dateFrom, Date dateTo){
        try {
            session = sessionFactory.openSession();
            tr = session.getTransaction();
            tr.begin();
                 Order order=creatObject(user,room,dateFrom,dateTo,0);
                 session.delete(order);
;
//                session.delete(hotel);
//                Order hotel = session.load(Hotel.class,50);
//                if (hotel != null) {
//                    session.delete(hotel);
//                }
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



    public static void main(String[] args) {
//        OrderDAO.delete(UserDAO.findById(17), RoomDAO.findById(12), new Date(), new Date());

    }



 //---------------------------------------------------------------------------------------------------------------
    private static Order creatObject(User user,Room room,Date dateFrom, Date dateTo, double moneyPaid) {
       Order order=new Order();
            order.setUser(user);
            order.setRoom(room);
            order.setDateFrom(dateFrom);
            order.setDateTo(dateTo);
            order.setMoneyPaid(moneyPaid);
    return order;
    }

}