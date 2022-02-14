package bHibernate.lesson2;

import java.util.Arrays;
import java.util.List;

public class Demo {
    public static void main(String[] args) {
        Product product=new Product();
            product.setName("Toy11");
            product.setDescription("Charma");
            product.setPrice(150);
        Product product2=new Product();
            product2.setName("Toy");
            product2.setDescription("Farvat");
            product2.setPrice(1050);
        Product product3=new Product();
            product3.setName("Toy333");
            product3.setDescription("LonfFarm");
            product3.setPrice(186);
        Product product4=new Product();
            product4.setName("Toy444");
            product4.setDescription("FromRoom");
            product4.setPrice(220);
        List<Product>  products= Arrays.asList(product2, product3, product4);

//        System.out.println(ProductDAO.save(product));
        System.out.println(ProductDAO.saveProducts(products));

//        System.out.println(ProductDAO.update(new Product(33, "newToy","newFarvater",1500)));
                Product product10=new Product(41, "aToy333","LonfFarm",179);
                Product product11=new Product(42, "zToy444","FromRoom",220);
            List<Product>  productss= Arrays.asList(product10, product11);
//        System.out.println(ProductDAO.updateAll(productss));

//        ProductDAO.delete(45);
        ProductDAO.deleteAll(productss);
    }
}
