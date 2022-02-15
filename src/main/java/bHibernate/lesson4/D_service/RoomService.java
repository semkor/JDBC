package bHibernate.lesson4.D_service;

import bHibernate.lesson4.A2_Exception.BadRequestException;
import bHibernate.lesson4.B_model.ENUM.UserType;
import bHibernate.lesson4.B_model.Filter;
import bHibernate.lesson4.B_model.Hotel;
import bHibernate.lesson4.B_model.Room;
import bHibernate.lesson4.E_DAO.HotelDAO;
import bHibernate.lesson4.E_DAO.RoomDAO;
import bHibernate.lesson4.E_DAO.UserDAO;

import java.text.*;
import java.util.*;

public class RoomService {
    private static RoomDAO roomDAO = new RoomDAO();

    public static List<Room> findRooms(Filter filter) {
        List<Room> rezult=roomDAO.findByParam (filter.getNumberOfGuests(),filter.getPrice(),filter.isBreakfastIncluded(),
                    filter.isPetsAllowed(),filter.getCountry(),filter.getCity());
        return rezult;
    }


//--------------------------------------------------- только для админа ---------------------------------------
    public static Room addRoom(Room room) {
            if (UserService.validateStatus()) {
                RoomDAO.save(room);
                return room;
            }
        return null;
    }

    public static void deleteRoom(long roomId) {
        if (UserService.validateStatus())
            roomDAO.delete(roomId);
    }
}
