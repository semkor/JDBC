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
        String sql = "SELECT * FROM HOTELFPR WHERE  NAME = :param";
        return hotelDAO.findByHotel(name, sql);
    }

    public static List<Hotel> findHotelByCity(String city) {
        String sql = "SELECT * FROM HOTELFPR WHERE  CITY = :param";
        return hotelDAO.findByHotel(city, sql);
    }

    //--------------------------------------------------- только для админа ---------------------------------------
    public static Hotel addHotel(Hotel hotel) {
        if (UserService.validateStatus()) {
            hotelDAO.save(hotel);
            return hotel;
        }
        return null;
    }

    public static void deleteHotel(long hotelId) {
        if (UserService.validateStatus())
            hotelDAO.delete(hotelId);
    }

}
