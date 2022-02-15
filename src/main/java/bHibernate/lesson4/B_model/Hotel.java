package bHibernate.lesson4.B_model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "HOTELFPR")
public class Hotel implements Serializable {
    private long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private List<Room> rooms;

    public Hotel() {
    }

    public Hotel(long id, String name, String country, String city, String street, List<Room> rooms) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.rooms = rooms;
    }

    //---------------------------------------------------- get ------------------------------------------------------------
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

    @Column(name = "COUNTRY")
    public String getCountry() {
        return country;
    }

    @Column(name = "CITY")
    public String getCity() {
        return city;
    }

    @Column(name = "STREET")
    public String getStreet() {
        return street;
    }

    @OneToMany (mappedBy = "hotel", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    public List<Room> getRooms() {
        return rooms;
    }

//---------------------------------------------------- set -------------------------------------------------------
    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    //---------------------------------------------------- other ---------------------------------------------------

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}