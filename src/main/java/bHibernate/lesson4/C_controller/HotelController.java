package bHibernate.lesson4.C_controller;

import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.D_service.HotelService;
import java.util.List;

public class HotelController {
    private static HotelService hotelService=new HotelService();

    public static List<Hotel> findHotelByName(String name){
        return hotelService.findByHotel(name);
    }

    public static List<Hotel>  findHotelByCity(String city){
        return hotelService.findHotelByCity(city);
    }

//-------------------------------------- только для админа ------------------------------------------------------
    public static Hotel addHotel(Hotel hotel){
        return hotelService.addHotel(hotel);
    }

//    public static void deleteHotel(long hotelId){
//        hotelService.deleteHotel(hotelId);
//    }
}