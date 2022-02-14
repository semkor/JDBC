package bHibernate.lesson4.F_Demo;


import bHibernate.lesson4.A2_Exception.BadRequestException;
import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.C_controller.HotelController;
import bHibernate.lesson4.C_controller.UserController;
import bHibernate.lesson4.D_service.UserService;
import bHibernate.lesson4.E_DAO.HotelDAO;

import java.util.List;

public class DemoHotel {
    public static void main(String[] args)throws BadRequestException {

// --------------------------------------------- 1 блок -----------------------------------------------------
// поиск  по имени
//        List<Hotel> hotels = HotelController.findHotelByName("Reikartz");
//        for(Hotel el:hotels){
//            System.out.println(el);
//        }
// поиск  по городу
//        List<Hotel> hotel = HotelController.findHotelByCity("Moscow");
//        for(Hotel el:hotel){
//            System.out.println(el);
//        }

// --------------------------------------------- 2 блок -----------------------------------------------------
// добавление отеля
    //такого отеля нет
//            Hotel hotel=new Hotel();
//                hotel.setName("Reikartz");
//                hotel.setCountry("Ukraine");
//                hotel.setCity("Kiev");
//                hotel.setStreet("Hreshatic");
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());
//        UserController.login("krom456","12323FiRA");
//        System.out.println(HotelController.addHotel(hotel));
    //не прошел валидацию
//        UserController.login("kRom456","12323FiRA");
//        System.out.println(HotelController.addHotel(hotel));
    //такой отель уже есть
//        UserController.login("krom456","12323FiRA");
//        Hotel hotel2=new Hotel();
//            hotel2.setName("Kim");
//            hotel2.setCountry("Ukraine");
//            hotel2.setCity("Kiev");
//            hotel2.setStreet("Lugova");
//        System.out.println(HotelController.addHotel(hotel));

// обновление данных отеля
//          Hotel hotel3=new Hotel();
//            hotel3.setId(26);
//            hotel3.setName("Kim");
//            hotel3.setCountry("Russian");
//            hotel3.setCity("Moscow");
//            hotel3.setStreet("Hreshatic");
//        System.out.println(HotelDAO.update(hotel3));

// --------------------------------------------- 3 блок -----------------------------------------------------
// удаление отеля
    //проходим валидацию
//        UserController.login("krom456","12323FiRA");
//        HotelDAO.delete(23);

    }
}
