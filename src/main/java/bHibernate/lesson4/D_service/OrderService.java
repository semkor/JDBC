package bHibernate.lesson4.D_service;

import bHibernate.lesson4.E_DAO.OrderDAO;
import bHibernate.lesson4.E_DAO.RoomDAO;
import bHibernate.lesson4.E_DAO.UserDAO;
import java.util.*;

public class OrderService {
    private static OrderDAO orderDAO = new OrderDAO();

    public static void bookRoom(long userId, long roomId, Date dateFrom, Date dateTo, double moneyPaid) {
        if (UserService.isStatus())
            orderDAO.save(UserDAO.findById(userId), RoomDAO.findById(roomId), dateFrom, dateTo, moneyPaid);
    }

    public static void cancelReservation(long userId, long roomId, Date dateFrom, Date dateTo) {
        if (UserService.isStatus())
            orderDAO.delete(UserDAO.findById(userId), RoomDAO.findById(roomId), dateFrom, dateTo);
    }
}
