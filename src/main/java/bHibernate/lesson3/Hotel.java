package bHibernate.lesson3;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "HOTELS")
public class Hotel {
    private long id;
    private String name;
    private String country;
    private String city;
    private String street;
    private Room room;

    public Hotel() {
    }

    public Hotel(long id, String name, String country, String city, String street, Room room) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.street = street;
        this.room = room;
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

    @OneToOne (optional = false, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn (name = "ROOM_ID")
    public Room getRoom() {
        return room;
    }

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

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", room=" + room +
                '}';
    }
}
