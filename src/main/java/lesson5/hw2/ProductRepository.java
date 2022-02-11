package lesson5.hw2;

import org.hibernate.Session;

public class ProductRepository {
    private static Session session=new HibernateUtils().createSessionFactory().openSession();

    public static Product save(Product product){
        session.getTransaction().begin();
        Product products=new Product();
            products.setId(product.getId());
            products.setName(product.getName());
            products.setDescription(product.getDescription());
            products.setPrice(product.getPrice());
        session.save(products);
        commitClose();
    return products;
    }

    public static Product update(Product product){
        session.getTransaction().begin();
        Product products=new Product();
            products.setId(product.getId());
            products.setName(product.getName());
            products.setDescription(product.getDescription());
            products.setPrice(product.getPrice());
        session.update(products);
        commitClose();
    return products;
    }

    public static void delete(long id){
        session.getTransaction().begin();
        Product products=new Product();
            products.setId(id);
        session.delete(products);
        commitClose();
    }

    private static void commitClose(){
        session.getTransaction().commit();
        System.out.println("This is OK");
        session.close();
    }
}
