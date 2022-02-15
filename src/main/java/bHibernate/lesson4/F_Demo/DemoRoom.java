package bHibernate.lesson4.F_Demo;

import bHibernate.lesson4.B_model.Filter;
import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.B_model.Room;
import bHibernate.lesson4.C_controller.RoomController;
import bHibernate.lesson4.C_controller.UserController;
import bHibernate.lesson4.E_DAO.RoomDAO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DemoRoom {
    public static void main(String[] args) {
        Date dateFrom=null;
        Date dateTo=null;
        try{
            String dateFromStr="02-01-2022";
            String dateToStr="04-04-2022";
            dateFrom=new SimpleDateFormat("dd-MM-yyyy").parse(dateFromStr);
            dateTo=new SimpleDateFormat("dd-MM-yyyy").parse(dateToStr);
        }catch(Exception e){
            System.out.println(e);
        }
// ------------------------------------------------ 1 блок -------------------------------------------------------------
//        System.out.println(RoomController.findRooms(new Filter(4,789.36,true,true,new Date(),"Russian","Moscow")));

// ------------------------------------------------ 2 блок -------------------------------------------------------------
// добавление комнаты
        Hotel hotel=new Hotel();
            hotel.setId(21);
        Room room=new Room();
            room.setHotel(hotel);
            room.setBreakfastIncluded(true);
            room.setPrice(127.54);
            room.setPetsAllowed(false);
            room.setNumberOfGuests(8);
//        UserController.login("krom456","12323FiRA");
//        System.out.println(RoomController.addRoom(room));

// обновление комнаты
//        Hotel hotel2=new Hotel();
//        hotel2.setId(27);
//        System.out.println(RoomDAO.update(new Room(30,3,159.25,true,false,new Date(),hotel2)));

// --------------------------------------------- 3 блок -----------------------------------------------------
//        UserController.login("krom456","12323FiRA");
//        RoomDAO.delete(26);

    }
}