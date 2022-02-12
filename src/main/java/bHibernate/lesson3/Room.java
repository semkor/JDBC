package bHibernate.lesson3;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name="ROOM")
public class Room {
    private long id;
    private int numberOfGuests;
    private double price;
    private int brekfastIncluded;
    private int petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room() {
    }

    public Room(long id, int numberOfGuests, double price, int brekfastIncluded, int petsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.brekfastIncluded = validate(brekfastIncluded, "brekfastIncluded");
        this.petsAllowed = validate(petsAllowed,"petsAllowed");
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    @Column(name = "NUMBER_OF_GUESTS")
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Column(name = "PRICE")
    public double getPrice() {
        return price;
    }

    @Column(name = "BREKFAST_INCLUDED")
    public int getBrekfastIncluded() {
        return brekfastIncluded;
    }

    @Column(name = "PETS_ALLOWED")
    public int getPetsAllowed() {
        return petsAllowed;
    }

    @Column(name = "DATE_AVAILABLE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn(name="HOTELS_ID")
    public Hotel getHotel() {
        return hotel;
    }


    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrekfastIncluded(int brekfastIncluded) {
            this.brekfastIncluded =validate(brekfastIncluded, "brekfastIncluded");
    }

    public void setPetsAllowed(int petsAllowed) {
            this.petsAllowed = validate(petsAllowed,"petsAllowed");
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    private static int validate(int n, String str) {
        if( n!=0 && n!=1 ) {
            System.err.println(str + " - can only be 0 or 1");
            System.exit(0);
        }
        return n;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", brekfastIncluded=" + brekfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                ", hotel=" + hotel +
                '}';
    }
}
