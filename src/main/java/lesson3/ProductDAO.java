package lesson3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {               //CRUD - (create(save), read (select), update, delete)
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    public Product save(Product product) {
        try (Connection connection = getConnection ();
             PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO PRODUCTM VALUES (?,?,?,?)")) {

            prepareStatement.setLong (1,product.getId());
            prepareStatement.setString (2,product.getName());
            prepareStatement.setString (3,product.getDescription());
            prepareStatement.setInt(4,product.getPrice());

            int res = prepareStatement.executeUpdate();
            System.out.println("Save was finished with result " + res);

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getProducts() {
        try (Connection connection = getConnection ();
             Statement statement = connection.createStatement()) {


            ResultSet resultSet=statement.executeQuery("SELECT * FROM PRODUCTM");
            List<Product> products=new ArrayList<>();
            while (resultSet.next()){
                Product product = new Product(resultSet.getLong(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
        return null;
    }

    public Product update(Product product) {
        try (Connection connection = getConnection ();
             PreparedStatement prepareStatement = connection.prepareStatement("UPDATE PRODUCTM SET NAME = ?, DESCRIPTON = ?, PRICE = ? WHERE ID = ?")) {
            prepareStatement.setString (1,product.getName());
            prepareStatement.setString (2,product.getDescription());
            prepareStatement.setInt(3,product.getPrice());
            prepareStatement.setLong (4,product.getId());

            int res = prepareStatement.executeUpdate();
            System.out.println("Update was finished with result " + res);

            if(res==0)
                return null;

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
        return product;
    }

    public void delete(long id) {
        try (Connection connection = getConnection ();
             PreparedStatement prepareStatement = connection.prepareStatement("DELETE FROM PRODUCTM WHERE ID = ?")) {
             prepareStatement.setLong (1,id);

             int res = prepareStatement.executeUpdate();
             System.out.println("Delete was finished with result " + res);

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }

    }

//--------------------------------------------------------------------------------------------------------------------
    private Connection getConnection () throws  SQLException{
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}