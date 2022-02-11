package lesson5.hw2;

public class Demo {
    public static void main(String[] args) {
        System.out.println(ProductRepository.save(new Product(93,"toyNew","blue & grey",147)));
        System.out.println(ProductRepository.update(new Product(93,"toy","blue",155)));
        ProductRepository.delete(93);
    }
}
