package bHibernate.lesson1.hw2;

import org.hibernate.Session;

public class ProductRepository {
    private static Session session=new HibernateUtils().createSessionFactory().openSession();

    public static Product save(Product product){
        session.getTransaction().begin();
        session.save(product);
        commitClose();
    return product;
    }

    public static Product update(Product product){
        session.getTransaction().begin();
        session.update(product);
        commitClose();
    return product;
    }

    public static void delete(long id){
        session.getTransaction().begin();
        Product product=new Product();
            product.setId(id);
        session.delete(product);
        commitClose();
    }

    private static void commitClose(){
        session.getTransaction().commit();
        System.out.println("This is OK");
        session.close();
    }
}
