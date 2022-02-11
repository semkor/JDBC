package aJDBC.lesson1and2;

import java.sql.*;
// 1. DB driver                     - драйвер базы данных
// 2. create connection             - создаем соединение
// 3. create query                  - создаем запрос
// 4. executy query
// 5. work with result
// 6. close all the connection

public class JDBCFirstStep {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                System.out.println("Class " + JDBC_DRIVER + " not found");
                return;
            }

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS")) {
                while (resultSet.next()) {
//пример 1 - получение данных из БД
                    //System.out.println(resultSet.getInt(1));
                    //System.out.println(resultSet.getString(2));
                    //System.out.println(resultSet.getInt(3));

////пример 2 - мапинг данных из БД
//                    long id=resultSet.getLong(1);
//                    String productName = resultSet.getString(2);
//                    int price = resultSet.getInt(3);
//                    Date dateOrdered=resultSet.getDate(4);
//                    Date dateConfirmed=resultSet.getDate(5);
//                        lesson1and2.Order order=new lesson1and2.Order(id,productName,price,dateOrdered,dateConfirmed);
//                        System.out.println(order);
                }
            }

////пример 3 - мапинг данных из БД  при указанных условиях
//            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM ORDERS WHERE PRICE >240")) {
//                while (resultSet.next()) {
//
//                    long id=resultSet.getLong(1);
//                    String productName = resultSet.getString(2);
//                    int price = resultSet.getInt(3);
//                    Date dateOrdered=resultSet.getDate(4);
//                    Date dateConfirmed=resultSet.getDate(5);
//                    lesson1and2.Order order=new lesson1and2.Order(id,productName,price,dateOrdered,dateConfirmed);
//                    System.out.println(order);
//                }
//            }

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }
}