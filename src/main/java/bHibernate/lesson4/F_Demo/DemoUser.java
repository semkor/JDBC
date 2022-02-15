package bHibernate.lesson4.F_Demo;

import bHibernate.lesson4.B_model.ENUM.UserType;
import bHibernate.lesson4.B_model.User;
import bHibernate.lesson4.D_service.UserService;
import bHibernate.lesson4.E_DAO.UserDAO;

public class DemoUser {
    public static void main(String[] args){
//---------------------------------------------------- 1 блок ---------------------------------------------------------
//----------------проверка юзера - пустые значения
//        UserService.login(null, null);

//----------------проверка юзера - существующего в базе
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());
//            UserService.login("krom456", "12323FiRA");
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());

//-----------------проверка юзера - не существующего в базе
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());
//              UserService.login("fRolov", "aD12345*");
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());

//-----------------удаление юзера
//            UserDAO.delete(29);

//-----------------сохранение юзера
//            User user=new User();
//        user.setUserName("lorkueu");
//        user.setPassword("28595AI*");
//        user.setCountry("Korea");
//        user.setUserType(UserType.USER);
//        UserDAO.save(user);

//---------------------------------------------------- 2 блок ---------------------------------------------------------
//регистрация  юзера - пустые значения
//        UserService.registerUser(new User());

//проверка регистрации в базе - логин существует
//            User user=new User();
//                user.setUserName("loyd45");
//                user.setPassword("2856595AI*");
//                user.setCountry("Korea");
//                user.setUserType(UserType.USER);
//        UserService.registerUser(user);

//проверка регистрации в базе - логин не существует в базе
//            User user=new User();
//                user.setUserName("loydreer45");
//                user.setPassword("2856595AI*");
//                user.setCountry("Korea");
//                user.setUserType(UserType.USER);
//        System.out.println(UserService.registerUser(user));


//---------------------------------------------------- 3 блок ---------------------------------------------------------
//проверка - выход из системы
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());
//        UserService.login("krom456", "12323FiRA");
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());
//        UserService.logout();
//        System.out.println(UserService.isStatus());
//        System.out.println(UserService.getUserType());
    }
}