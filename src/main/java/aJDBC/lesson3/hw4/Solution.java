package aJDBC.lesson3.hw4;

import java.sql.*;

public class Solution {
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

// ------------------------------------- добавляет 1000 записей с произвольными значениями------------------------------
    public void testSavePerfomance() {
        try (Connection connection = getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO TEST_SPEED VALUES (?,?,?)")) {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                prepareStatement.setInt(1, i);
                prepareStatement.setString(2, ("toy" + i));
                prepareStatement.setInt(3, (25 + i));
                int res = prepareStatement.executeUpdate();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

// ------------------------------------- удаляет 1000 записей отдельными запросами по полю ID --------------------------
    public void testDeleteByIdPerfomance () {
        try (Connection connection = getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM TEST_SPEED WHERE ID = ?")) {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                prepareStatement.setInt(1, i);
                int res = prepareStatement.executeUpdate();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

// ------------------------------------- удаляет 1000 записей одним запросом SQL ---------------------------------------
    public void testDeletePerfomance() {
        try (Connection connection = getConnection();
        Statement statement = connection.createStatement()) {
            long startTime = System.currentTimeMillis();
            int response = statement.executeUpdate("DELETE FROM TEST_SPEED LIMIT 1000");
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

// ------------------------------------- выбирает 1000 записей отдельным запросом по полю ID ---------------------------
    public void testSelectByIdPerfomance() {
        try (Connection connection = getConnection();
        PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM TEST_SPEED WHERE ID = ?")) {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < 1000; i++) {
                prepareStatement.setInt(1, i);
                ResultSet resultSet  = prepareStatement.executeQuery();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

// ------------------------------------- выбирает 1000 записей одним запросом SQL --------------------------------------
    public void testSelectPerfomance(){
        try (Connection connection = getConnection();
        Statement statement = connection.createStatement()) {
            long startTime = System.currentTimeMillis();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM TEST_SPEED LIMIT 1000");
            long endTime = System.currentTimeMillis();
            System.out.println("That took " + (endTime - startTime) + " milliseconds");
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
