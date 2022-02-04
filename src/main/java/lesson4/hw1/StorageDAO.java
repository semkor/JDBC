package lesson4.hw1;

import java.sql.*;

public class StorageDAO {
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    public static Storage save (Storage storage){
        try (Connection connection = getConnection ();
             PreparedStatement prepareStatement = connection.prepareStatement(
                     "INSERT INTO STORAGE VALUES (?,?,?,?)")) {

             prepareStatement.setLong (1,storage.getId());
             prepareStatement.setString(2, convert(storage.getFormatsSupported()));
             prepareStatement.setString(3, storage.getStorageCountry().toUpperCase());
             prepareStatement.setLong(4, storage.getStorageMaxSize());

             int res = prepareStatement.executeUpdate();
                System.out.println("Save was finished with result " + res);

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
            return null;
        }
        return storage;
    }

    public static Storage update (Storage storage) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(
                     "UPDATE STORAGE SET FORMAT_SUPPORTED = ?, STORAGE_COUNTRY = ?, MAX_SIZE = ? WHERE ID = ?")) {

             prepareStatement.setString(1, convert(storage.getFormatsSupported()));
             prepareStatement.setString(2, storage.getStorageCountry());
             prepareStatement.setLong(3, storage.getStorageMaxSize());
             prepareStatement.setLong(4, storage.getId());

             int res = prepareStatement.executeUpdate();
             if (res == 0) {
                System.err.println("Update was finished with result - " + res + " - operation not successful");
                return null;
             }
             System.out.println("Update was finished with result - " + res + " - successful operation");

        } catch(SQLException e){
                System.err.println("Something  went wrong");
                e.printStackTrace();
        }
        return storage;
    }

    public static Storage findById(long id) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM STORAGE WHERE ID = ?")) {
             prepareStatement.setLong (1,id);

             ResultSet resultSet = prepareStatement.executeQuery();
             Storage storage=null;

             while(resultSet.next()) {
                 storage = new Storage(resultSet.getLong(1), resultSet.getString(2).split(","), resultSet.getString(3), resultSet.getLong(4));
             }
             return storage;
        } catch(SQLException e){
             System.err.println("Something  went wrong");
             e.printStackTrace();
        }
        return null;
    }

    public static void delete (long id){
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM STORAGE WHERE ID = ?")) {
             prepareStatement.setLong (1,id);
             int res = prepareStatement.executeUpdate();
             System.out.println("Delete was finished with result " + res);
        } catch (SQLException e) {
            System.err.println("Something  went wrong");
            e.printStackTrace();
        }
    }

//---------------------------------------------------------------------------------------------------------------------
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private static String convert(String[] format){
        String newFormat=format[0];
        for (int i=1; i<format.length;i++){
            newFormat+=","+format[i];
        }
    return newFormat;
    }
}
