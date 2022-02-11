package aJDBC.lesson4.hw1;

import java.sql.*;
import java.util.*;

public class FileDAO {
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    private static final String sqlDelete = "DELETE FROM FILE WHERE ID = ?";
    private static final String sqlFindId = "SELECT * FROM FILE WHERE  ID = ?";
    private static final String sqlFindIStorage = "SELECT * FROM FILE WHERE  STORAGE_ID = ?";

    public static List<File> save(List<File> file) {
        try (Connection connection = getConnection()) {
            saveList(file, connection);

        } catch (SQLException e) {
            System.err.println("Something  went wrong");
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public static List<File> update(List<File> file) {
        try (Connection connection = getConnection()) {
            updateList(file, connection);

        } catch (SQLException e) {
            System.err.println("Something  went wrong");
            e.printStackTrace();
            return null;
        }
        return file;
    }

    public static List<File> findById(long id) {
        return findByIdQuery(id, sqlFindId);
    }

    public static List<File> findByIdStorage(long id) {
        return findByIdQuery(id, sqlFindIStorage);
    }

    public static void delete(long id) {
        delete(id, sqlDelete);
    }


    //------------------------------------------------------------------------------------------------------------------

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPass() {
        return PASS;
    }

    //------------------------------------------------------------------------------------------------------------------
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private static void saveList(List<File> file, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO FILE VALUES (?,?,?,?,?)")) {
            connection.setAutoCommit(false);

            for (File el : file) {
                prepareStatement.setLong(1, el.getId());
                prepareStatement.setString(2, el.getName());
                prepareStatement.setString(3, el.getFormat());
                prepareStatement.setLong(4, el.getSize());
                if (el.getStorage() == null)
                    prepareStatement.setString(5, null);
                else
                    prepareStatement.setLong(5, el.getStorage().getId());
                int res = prepareStatement.executeUpdate();
                System.out.println("Save was finished with result " + res);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    private static void updateList(List<File> file, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection.prepareStatement(
                "UPDATE FILE SET FILE.NAME = ?, FILE.FORMAT = ?, FILE.SIZE = ?, FILE.STORAGE_ID= ? WHERE ID = ?")) {
            connection.setAutoCommit(false);

            for (File el : file) {
                prepareStatement.setString(1, el.getName());
                prepareStatement.setString(2, el.getFormat());
                prepareStatement.setLong(3, el.getSize());
                if (el.getStorage() == null) {
                    prepareStatement.setString(4, null);
                } else {
                    prepareStatement.setLong(4, el.getStorage().getId());
                }
                prepareStatement.setLong(5, el.getId());

                int res = prepareStatement.executeUpdate();
                if (res == 0) {
                    System.err.println("Update was finished with result - " + res + " - operation not successful");
                }
                System.out.println("Update was finished with result - " + res + " - successful operation");
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    public static void delete(long id, String sql) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM FILE WHERE ID = ?")) {
            prepareStatement.setLong(1, id);

            int res = prepareStatement.executeUpdate();
            System.out.println("Delete was finished with result " + res);
        } catch (SQLException e) {
            System.err.println("Something  went wrong");
            e.printStackTrace();
        }
    }

    private static File getFileFromResultSet(ResultSet rs) throws SQLException {
        File file = new File(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getLong(4),
                StorageDAO.findById(rs.getLong(5)));
        return file;
    }

    private static List<File> findByIdQuery(long id, String sql) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(sql)) {
            prepareStatement.setLong(1, id);

            ResultSet rs = prepareStatement.executeQuery();
            List<File> files = new ArrayList<>();
            while (rs.next()) {
                files.add(getFileFromResultSet(rs));
            }
            return files;
        } catch (SQLException e) {
            System.err.println("Something  went wrong");
            e.printStackTrace();
        }
        return null;
    }
}
