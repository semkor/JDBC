package bHibernate.lesson4.D_service;

import bHibernate.lesson4.A2_Exception.BadRequestException;
import bHibernate.lesson4.B_model.ENUM.UserType;
import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.E_DAO.HotelDAO;
import bHibernate.lesson4.E_DAO.UserDAO;

import java.util.*;

public class HotelService {
    private static HotelDAO hotelDAO = new HotelDAO();

    public static List<Hotel> findByHotel(String name) {
        String sql= "SELECT * FROM HOTELFPR JOIN ROOMFPR ON ROOMFPR.HOTELFPR_ID=HOTELFPR.ID WHERE  NAME = :param";
        return hotelDAO.findByHotel(name,sql);
    }

    public static List<Hotel> findHotelByCity(String city) {
        String sql= "SELECT * FROM HOTELFPR JOIN ROOMFPR ON ROOMFPR.HOTELFPR_ID=HOTELFPR.ID WHERE  CITY = :param";
        return hotelDAO.findByHotel(city,sql);
    }

 //--------------------------------------------------- только для админа ---------------------------------------
    public static Hotel addHotel(Hotel hotel) {
       try {
           if (validateUser() || HotelDAO.findByFourParam(hotel.getName(),hotel.getCountry(),hotel.getCity(),hotel.getStreet())!=null)
                    throw new BadRequestException("No rights or Hotel with such parameters exists");
                hotelDAO.save(hotel);
           } catch (BadRequestException e) {
                System.err.println("Error: " + e.getMessage());
                return null;
            }
        return hotel;
    }

//нужно прописать, чтобы удаляло и комнаты, которые привязаны к отелю - каскадное удаление
//    public static void deleteHotel(long hotelId) {
//            validateUser();
//            hotelDAO.delete(hotelId);
//    }

//----------------------------------------------------------------------------------------------------------------
     public static boolean validateUser(){
         boolean statusActive = UserService.isStatus();
         UserType userType = UserService.getUserType();
         if (!UserService.isStatus() && UserService.getUserType() != UserType.ADMIN)
             return true;
        return false;
     }
}
