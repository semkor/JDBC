package aJDBC.lesson4;

import aJDBC.lesson3.Product;
import java.sql.*;
import java.util.*;

public class TransactionDemo {
    //1. save order - pay money - receive money
    //2. save order - pay money - receive money
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    public static void save(List<Product> products) {
        try (Connection connection = getConnection ()){
                saveList (products, connection);
        } catch (SQLException e) {
            System.err.println("Something  went wrong");
            e.printStackTrace();
        }
    }

//--------------------------------------------------------------------------------------------------------------------
    private static Connection getConnection() throws  SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private static void saveList (List<Product> products, Connection connection) throws SQLException {
        try(PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO PRODUCTM VALUES (?,?,?,?)")) {

            connection.setAutoCommit(false);

            for(Product product:products) {
                prepareStatement.setLong(1, product.getId());
                prepareStatement.setString(2, product.getName());
                prepareStatement.setString(3, product.getDescription());
                prepareStatement.setInt(4, product.getPrice());

                int res = prepareStatement.executeUpdate();
                System.out.println("Save was finished with result " + res);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}