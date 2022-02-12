package bHibernate.lesson3;

import javax.xml.validation.Validator;
import java.util.Date;

public class Demo {
    public static void main(String[] args) {
        Hotel hotel=new Hotel();
            hotel.setName("SoulPlace");
            hotel.setCountry("Korea");
            hotel.setCity("Inchxon");
            hotel.setStreet("Kimminsu");
//      System.out.println(HotelDAO.save(hotel));
//      System.out.println(HotelDAO.update(new Hotel(4,"SoulPlaceNew","English","London","Bendli")));
//      System.out.println(HotelDAO.findById(4));
//      HotelDAO.delete(4);


            Room room=new Room();
                room.setNumberOfGuests(3);
                room.setPrice(123.45);
                room.setBrekfastIncluded(1);
                room.setPetsAllowed(1);
                room.setDateAvailableFrom(new Date());
                room.setHotel(new Hotel(1,"Reilkartz","Ukraine","Kiev","Hrechatic"));
      System.out.println(RoomDAO.save(room));
//      System.out.println(RoomDAO.update(new Room(12,75,125.45,1,
//                    0,new Date(),new Hotel(1,"Reilkartz","Ukraine","Kiev","Hrechatic"))));
//      RoomDAO.delete(10);
//      System.out.println(RoomDAO.findById(10));

    }
}
