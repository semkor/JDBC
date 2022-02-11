package bHibernate.lesson2;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTHB")
public class Product {
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

    /*  если ORACLE
            CREATE SEQUENCE PRODUCTM_SEQ MAINVALUE 1 MAXVALUE 100 START WITH 1 INCREMENT BY 2;
            DROP SEQUENCE PRODUCTM_SEQ;

            @SequenceGenerator(name="PR_SEQ",sequenceName = "PRODUCTM_SEQ",allocationSize = 1);
            @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PR_SEQ");
        */
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
