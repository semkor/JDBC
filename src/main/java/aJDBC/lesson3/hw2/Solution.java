package aJDBC.lesson3.hw2;

import org.apache.commons.lang3.StringUtils;
import java.sql.*;
import java.util.*;

public class Solution {
    private static final String DB_URL = "jdbc:mysql://database-1.c0nqcnerv9wp.us-east-1.rds.amazonaws.com:3306/database_name";
    private static final String USER = "maven";
    private static final String PASS = "1291328diMA";

    //a. поиск  продуктов с заданной ценой в диапазоне +-delta включительно (при 100 будет искать от 90 до 110)
    public List<Product> findProductsByPrice(int price, int delta) {
        try (Connection connection = getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM PRODUCTM WHERE PRICE BETWEEN ? AND ?")) {
            prepareStatement.setInt(1, (price - delta));
            prepareStatement.setInt(2, (price + delta));

            List<Product> products = new ArrayList<>();
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
        return null;
    }

    /*b. продукты, которые содержат в своем имени слово word*
       * - если слово word является неккоректным (больше одного слова в стринге, длина меньше 3, содержит спецсимволы)
                  - тогда выбрасывает ошибку, которая в описании обязательно должна содержать само слово и описание ошибки*/
    public List<Product> findProductsByName(String word) {

        try (Connection connection = getConnection(); PreparedStatement prepareStatement = connection.prepareStatement("SELECT * FROM PRODUCTM WHERE NAME  LIKE ? ")) {
            prepareStatement.setString(1, ("%" + word + "%"));

            ResultSet resultSet = prepareStatement.executeQuery();
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                String validation = resultSet.getString(2);
                Product product = validate(resultSet,validation, word);
                if(product != null)
                    products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
        return null;
    }

    //с. продукты с пустым полем описания
    public List<Product> findProductsWithEmptyDescription() {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT *, LENGTH (DESCRIPTON) FROM PRODUCTM WHERE LENGTH (DESCRIPTON)=0");
            List<Product> products = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                products.add(product);
            }
            return products;

        } catch (SQLException e) {
            System.out.println("Something  went wrong");
            e.printStackTrace();
        }
        return null;
    }

//----------------------------------------------------------------------------------------------------------------------
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    private Product validate(ResultSet resultSet,String validation, String word) {
        try {
            if (validation.length() < 3)
                throw new SQLException("The given word: " + validation + " - is not correct " + " - less than 3 letters");
            else if (!validation.matches("[a-zA-z]+"))
                throw new SQLException("The given word: " + validation + " - is not correct " + " - there are special characters");
            else if ((StringUtils.countMatches(validation.toLowerCase(), word)) > 1)
                throw new SQLException("The given word: " + validation + " - is not correct " + " - more than 1 word repetitions");
            else {
                Product product = new Product(resultSet.getLong(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4));
                return product;
            }
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}