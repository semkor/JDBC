package hw1;

import java.sql.*;

public class Solution {
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    public static void main(String[] args) {
//первая часть
        getAllProducts();
        getProductsByPrice();
        getProductsByDescription();

//вторая часть
        saveProduct();
        deleteProducts();
        deleteProductsByPrice();

//третья часть
        increasePrice();
        changeDescription();

    }

//------------------------------------------------ 1 часть ------------------------------------------------------------

    private static void getAllProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTM")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }


    private static void getProductsByPrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM PRODUCTM WHERE PRICE<100")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

    private static void getProductsByDescription() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery("SELECT *, LENGTH (DESCRIPTON) FROM PRODUCTM WHERE LENGTH (DESCRIPTON) > 50")) {
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(2));
                }
            }
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

//------------------------------------------------ 2 часть ------------------------------------------------------------

    private static void saveProduct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            int response = statement.executeUpdate("INSERT INTO PRODUCTM VALUES (999,'toy','for children',60)");
            System.out.println(response);
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

    private static void deleteProducts() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            int response = statement.executeUpdate("DELETE FROM PRODUCTM WHERE NAME != 'toy'");
            System.out.println(response);
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

    private static void deleteProductsByPrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            int response = statement.executeUpdate("DELETE FROM PRODUCTM WHERE PRICE < 100");
            System.out.println(response);
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

//------------------------------------------------ 3 часть ------------------------------------------------------------
    private static void increasePrice() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            int response = statement.executeUpdate("UPDATE PRODUCTM SET PRICE = PRICE + 100 WHERE PRICE < 970");
            System.out.println(response);
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

    private static void changeDescription() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            int response = statement.executeUpdate("UPDATE PRODUCTM SET DESCRIPTON = CONCAT(SUBSTRING_INDEX (DESCRIPTON, '. ',2),'.') WHERE LENGTH (DESCRIPTON) > 50");
            System.out.println(response);
        } catch (SQLException e) {
            System.out.println("Something  went  wrong");
            e.printStackTrace();
        }
    }
}