import java.sql.*;

public class JDBCExamples {
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//
//    static {
//        try {
//            Class.forName(JDBC_DRIVER);
//        } catch (ClassNotFoundException e) {
//            System.out.println("Class " + JDBC_DRIVER + " not found");
//        }
//    }

    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS); Statement statement = connection.createStatement()) {

////пример 1 - добавление данных в таблицу
//            boolean res = statement.execute("INSERT INTO PRODUCTM VALUES (123,'toy1','for children',60)");
//            System.out.println(res);
//            boolean res1 = statement.execute("INSERT INTO PRODUCTM VALUES (124,'toy2','for children',60)");
//            boolean res2 = statement.execute("INSERT INTO PRODUCTM VALUES (125,'toy3','for children',60)");


//пример 2 - запрос на удаление/добавление данных из таблицы
//            boolean res = statement.execute("DELETE FROM PRODUCTM WHERE NAME = 'toy1'");
//            System.out.println(res);
//
//            int response = statement.executeUpdate("INSERT INTO PRODUCTM VALUES (127,'toy5','for children',60)");
//            System.out.println(response);

//пример 3 - запрос на удаление данных из таблицы
//            int response = statement.executeUpdate("DELETE FROM PRODUCTM WHERE NAME = 'toy5'");
//            System.out.println(response);

//пример 4 - запрос на удаление данных из таблицы
            int response = statement.executeUpdate("DELETE FROM PRODUCTM WHERE NAME = 'car'");
            System.out.println(response);


        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }
}