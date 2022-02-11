package bHibernate.lesson2.hw1;

import bHibernate.lesson2.Product;

import java.util.Arrays;
import java.util.List;

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
