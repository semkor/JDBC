package bHibernate.lesson4.C_controller;

import bHibernate.lesson4.B_model.Filter;
import bHibernate.lesson4.B_model.Room;
import bHibernate.lesson4.D_service.RoomService;

import java.util.List;
import java.util.Set;

public class RoomController {
    private static RoomService roomService = new RoomService();

    public static List<Room> findRooms(Filter filter) {
        return roomService.findRooms(filter);
    }

//--------------------------------- только для админа  -----------------------------------------------
    public static Room addRoom(Room room) {
        return roomService.addRoom(room);
    }
//
//    public static void deleteRoom(long RoomId) {
//        roomService.deleteRoom(RoomId);
//    }
}