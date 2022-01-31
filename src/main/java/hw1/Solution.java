package hw1;

import java.sql.*;

public class Solution {
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    public static void main(String[] args) {
        try {
            getAllProducts();
            getProductsByPrice();
            getProductsByDescription();
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }

    }

    private static void getAllProducts() throws SQLException {
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
}