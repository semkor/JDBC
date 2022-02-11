package bHibernate.lesson2.hw1;

import org.hibernate.*;
import org.hibernate.type.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import java.util.*;

public class ProductDAO {
    private static SessionFactory sessionFactory;
    private static Session session = null;
    private static Transaction tr = null;

    final private static String findById = "SELECT * FROM PRODUCTHB WHERE ID = ?";
    final private static String findByName = "SELECT * FROM PRODUCTHB WHERE NAME = ?";
    final private static String findByContainedName = "SELECT * FROM PRODUCTHB WHERE NAME LIKE ?";
    final private static String findByPrice = "SELECT * FROM PRODUCTHB WHERE PRICE BETWEEN ? AND ?";
    final private static String findByNameSortedAsc = "SELECT * FROM PRODUCTHB WHERE NAME = ?  ORDER BY NAME ASC";
    final private static String findByNameSortedDesc = "SELECT * FROM PRODUCTHB WHERE NAME = ? ORDER BY NAME DESC";
    final private static String findByPriceSortedDesc = "SELECT * FROM PRODUCTHB WHERE PRICE BETWEEN ? AND ? ORDER BY PRICE DESC";


//--- поиск продукта по ID
    public static Product findById(long id) {
        Product product = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
               NativeQuery nq = session.createNativeQuery(findById, Product.class);
               nq.setParameter(1, id);
               product = (Product) nq.getSingleResult();
            tr.commit();
            System.out.println("Find by ID is done");
        } catch (HibernateException e) {
            System.err.println("Find by ID is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return product;
    }


//--- поиск продуктов по имени
    public static List<Product> findByName(String name) {
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                products = nativeQueryString(name,findByName);
            tr.commit();
            System.out.println("Find by name done");
        } catch (HibernateException e) {
            System.err.println("Find by name is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

//--- поиск продуктов, которые в своем имени содержат слово name
    public static List<Product> findByContainedName(String name) {
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                NativeQuery nq = session.createNativeQuery(findByContainedName, Product.class);
                nq.setParameter(1, "%" + name + "%");
                products = nq.getResultList();
            tr.commit();
            System.out.println("Find by name done");
        } catch (HibernateException e) {
            System.err.println("Find by name is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

//--- поиск продуктов по вилке цен price +- delta включительно
    public static List<Product> findByPrice(int price, int delta) {
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                products = nativeQueryPrice(price,delta,findByPrice);
            tr.commit();
            System.out.println("Find by name done");
        } catch (HibernateException e) {
            System.err.println("Find by name is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

//--- поиск продуктов по имени, результат отсортирован по алфавитному порядку (колонка name)
    public static List<Product> findByNameSortedAsc(String name) {
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                products = nativeQueryString(name,findByNameSortedAsc);
            tr.commit();
            System.out.println("Find by name done");
        } catch (HibernateException e) {
            System.err.println("Find by name is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

//--- поиск продуктов по имени, результат отсортирован по алфавитному порядку  (колонка name)
    public static List<Product> findByNameSortedDesc(String name) {
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                products = nativeQueryString(name,findByNameSortedDesc);
            tr.commit();
            System.out.println("Find by name done");
        } catch (HibernateException e) {
            System.err.println("Find by name is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

//--- поиск продуктов по вилке цен price+-delta включительно, результат  отсортированы по убыванию
    public static List<Product> findByPriceSortedDesc(int price, int delta) {
        List<Product> products = null;
        try {
            session = createSessionFactory().openSession();
            tr = session.getTransaction();
            tr.begin();
                products = nativeQueryPrice(price,delta,findByPriceSortedDesc);
            tr.commit();
            System.out.println("Find by name done");
        } catch (HibernateException e) {
            System.err.println("Find by name is failed");
            System.out.println(e.getMessage());
            if (tr != null)
                tr.rollback();
        } finally {
            if (session != null)
                session.close();
        }
        return products;
    }

//---------------------------------------------------------------------------------------------------------------------
    public static SessionFactory createSessionFactory() {
        //singleton pattern
        if (sessionFactory == null)
            sessionFactory = new Configuration().configure().buildSessionFactory();
        return sessionFactory;
    }

    private static List<Product> nativeQueryString(String name, String sql) {
        NativeQuery nq = session.createNativeQuery(sql, Product.class);
        nq.setParameter(1, name);
    return nq.getResultList();
    }

    private static List<Product> nativeQueryPrice(int price, int delta, String sql) {
        NativeQuery nq = session.createNativeQuery(sql, Product.class);
        nq.setParameter(1, price-delta);
        nq.setParameter(2, price+delta);
        return nq.getResultList();
    }

}
