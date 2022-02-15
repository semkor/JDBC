package bHibernate.lesson4.C_controller;

import bHibernate.lesson4.D_service.OrderService;
import java.util.Date;

public class OrderController {
    private static OrderService orderService=new OrderService();

    public static void bookRoom(long userId,long roomId,Date dateFrom, Date dateTo,double moneyPaid){
        orderService.bookRoom(userId,roomId,dateFrom,dateTo,moneyPaid);
    }

    public static void cancelReservation(long userId,long roomId){
        orderService.cancelReservation(userId,roomId);
    }
}