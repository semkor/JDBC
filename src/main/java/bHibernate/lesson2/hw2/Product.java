package bHibernate.lesson2.hw2;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRODUCTHB")
public class Product implements Serializable {
    private long id;
    private String name;
    private String description;
    private int price;

    public Product() {
    }

    public Product(long id, String name, String description, int price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Column(name = "DESCRIPTON")
    public String getDescription() {
        return description;
    }

    @Column(name = "PRICE")
    public int getPrice() {
        return price;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
