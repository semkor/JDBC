package bHibernate.lesson1.hw1;

import org.hibernate.Session;

public class Demo {
    public static void main(String[] args) {
        save();
    }

    public static void save(){
        Session session=new HibernateUtils().createSessionFactory().openSession();
        session.getTransaction().begin();
        Product product=new Product();
            product.setId(87);
            product.setName("toyNew");
            product.setDescription("blue & grey");
            product.setPrice(147);
        session.save(product);
        session.getTransaction().commit();
            System.out.println("This is OK");
        session.close();
    }
}
