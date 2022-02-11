package bHibernate.lesson2.hw2;

public class Demo {
    public static void main(String[] args) {
        System.out.println(ProductDAO.findById(41));
        System.out.println(ProductDAO.findByName("Toy"));
        System.out.println(ProductDAO.findByContainedName("name"));
        System.out.println(ProductDAO.findByPrice(220, 40));
        System.out.println(ProductDAO.findByNameSortedAsc("Toy"));
        System.out.println(ProductDAO.findByNameSortedDesc("Toy"));
        System.out.println(ProductDAO.findByPriceSortedDesc(220, 40));
    }
}
