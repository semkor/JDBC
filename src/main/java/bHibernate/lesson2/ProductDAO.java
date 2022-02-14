package bHibernate.lesson2;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ProductDAO {
    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static Transaction tr = null;

    //-------------------------------------------save-----------------------------------------------------------------------
    public static Product save(Product product) {
        try {
            session = createSessionFactory().openSession();             //1. create session & transaction
            tr = session.getTransaction();
            tr.begin();
            session.save(product);                                      //2. action
            tr.commit();                                                //3. close session & transaction
            System.out.println("Save is done");
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    return product;
    }

    public static List<Product> saveProducts(List<Product> products) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            for (Product el : products) {
                session.save(el);
            }
            tr.commit();
            System.out.println("Save is done");
        } catch (HibernateException e) {
            System.err.println("Save is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

    //-----------------------------------------------update-----------------------------------------------------------------
    public static Product update(Product product) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            session.update(product);
            tr.commit();
            System.out.println("Update is done");
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return product;
    }

    public static List<Product> updateAll(List<Product> products) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            for (Product el : products) {
                session.update(el);
            }
            tr.commit();
            System.out.println("Update is done");
        } catch (HibernateException e) {
            System.err.println("Update is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

//-----------------------------------------------delete-----------------------------------------------------------------
    public static void delete(long id) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            Product product=(Product) session.get(Product.class,id);
            if(product !=null)
                session.delete(product);
            tr.commit();
            System.out.println("Delete is done");
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }

    public static void deleteAll(List<Product> products) {
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
            for (Product el:products) {
                Product product=(Product) session.get(Product.class,el.getId());
                if(product !=null)
                    session.delete(product);
            }
            tr.commit();
            System.out.println("Delete is done");
        } catch (HibernateException e) {
            System.err.println("Delete is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
    }

//---------------------------------------------------------------------------------------------------------------------
    public static SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }
}
