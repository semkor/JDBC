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

            try (ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST")) {
                while (resultSet.next()) {
                    System.out.println("Object found");
                }
            }

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }
}