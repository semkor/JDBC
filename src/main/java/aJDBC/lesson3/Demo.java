package aJDBC.lesson3;

public class Demo {
    public static void main(String[] args) {
        ProductDAO productDAO=new ProductDAO();
        Product product = new Product(59, "test", "test description", 99);

//save DB
//        productDAO.save(product);

//read DB
        System.out.println(productDAO.getProducts());

//update DB
//        System.out.println(productDAO.getProducts());

//delete DB
//        System.out.println(productDAO.update(new Product(61, "testNEW", "test descriptionNEW", 109)));
    }
}
