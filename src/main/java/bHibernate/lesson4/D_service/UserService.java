package bHibernate.lesson4.D_service;

import bHibernate.lesson4.A2_Exception.BadRequestException;
import bHibernate.lesson4.B_model.ENUM.UserType;
import bHibernate.lesson4.B_model.User;
import bHibernate.lesson4.E_DAO.UserDAO;
import org.hibernate.HibernateException;

import java.util.Map;

public class UserService {
    private static boolean statusActive = false;
    private static UserType userType=UserType.USER;
    private static UserDAO userDAO = new UserDAO();


    public static void login(String UserName, String password) {
        try {
            validateUser(UserName, password, "");
            User user= userDAO.findById(UserName, password);
            if(user == null)
                throw new BadRequestException("Incorrect login or password");
            statusActive = true;
            userType=user.getUserType();
        } catch (BadRequestException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static User registerUser(User user) {;
        try {
            validateUser(user.getUserName(), user.getPassword(), user.getCountry());
            return userDAO.save(user);
        } catch (BadRequestException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static void logout() {
        statusActive = false;
        userType=UserType.USER;
        System.out.println("Waiting again. Bye bye!!!");
    }

///----------------------------------------------------- other -------------------------------------------------
    public static boolean isStatus() {
        return statusActive;
    }

    public static UserType getUserType() {
        return userType;
    }

    private static void validateUser(String name, String password, String country) throws BadRequestException {
        if (name == null || password == null || country == null)
                throw new BadRequestException("Fields must not be empty");
    }
}
