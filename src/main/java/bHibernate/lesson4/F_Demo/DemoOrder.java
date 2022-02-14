package bHibernate.lesson4.F_Demo;

import bHibernate.lesson4.C_controller.OrderController;
import bHibernate.lesson4.C_controller.UserController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoOrder {
    public static void main(String[] args) throws Exception {
        Date dateFrom=null;
        Date dateTo=null;
        try{
            String dateFromStr="15-01-2022";
            String dateToStr="20-01-2022";
            dateFrom=new SimpleDateFormat("dd-MM-yyyy").parse(dateFromStr);
            dateTo=new SimpleDateFormat("dd-MM-yyyy").parse(dateToStr);
        }catch(Exception e){
            System.out.println(e);
        }
//// ----------------------------------------------- 1 блок -------------------------------------------------------------
//бронирование ордера
//        UserController.login("krom456","12323FiRA");
//        OrderController.bookRoom(17, 12, dateFrom, dateTo, 2550.45);

// ----------------------------------------------- 2 блок -------------------------------------------------------------
//удаление брони
        //(с 15-01-2022 по 20-01-2022)
//        OrderController.cancelReservation(106, 4000000033l, dateFrom, dateTo);
//
//        UserController.login("lorder","34592");
//        OrderController.cancelReservation(106, 4000000033l, dateFrom, dateTo);
    }
}