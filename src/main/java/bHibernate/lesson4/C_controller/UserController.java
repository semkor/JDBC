package bHibernate.lesson4.C_controller;

import bHibernate.lesson4.B_model.User;
import bHibernate.lesson4.D_service.UserService;

public class UserController {
    private static UserService userService = new UserService();

    public static void login(String UserName, String password) {
        userService.login(UserName, password);
    }

    public static User registerUser(User user) {
        return userService.registerUser(user);
    }

    public static void logout() {
        userService.logout();
    }
}