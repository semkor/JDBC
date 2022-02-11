package aJDBC.lesson4;

import aJDBC.lesson3.Product;
import java.util.*;

public class Demo {
    public static void main(String[] args) {
//пример 1 - сохранение нормальное - комит произошел
//        Product product=new Product(22, "EEE","test description",99);
//        Product product1=new Product(23, "EEE","test description",99);
//        Product product2=new Product(24, "EEE","test description",99);

//пример 2 - при сохранении - будет падать последний продукт - комит не произошел
        Product product=new Product(75, "EEE","test description",99);
        Product product1=new Product(76, "EEE","test description",99);
        Product product2=new Product(76, "EEE","test description",99);

        List<Product> products=new ArrayList<>();
        products.add(product);
        products.add(product1);
        products.add(product2);

        TransactionDemo.save(products);
    }
}
