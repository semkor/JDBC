package bHibernate.lesson4.B_model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ROOMFPR")
public class Room {
    private long id;
    private int numberOfGuests;
    private double price;
    private boolean breakfastIncluded;
    private boolean petsAllowed;
    private Date dateAvailableFrom;
    private Hotel hotel;

    public Room() {
    }

    public Room(long id, int numberOfGuests, double price, boolean breakfastIncluded, boolean petsAllowed, Date dateAvailableFrom, Hotel hotel) {
        this.id = id;
        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.hotel = hotel;
    }

//---------------------------------------------------- get ------------------------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public long getId() {
        return id;
    }

    @Column(name="NUMBER_OF_GUESTES")
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Column(name="PRICE")
    public double getPrice() {
        return price;
    }

    @Column(name="BREKFAST_INCLUDED")
    public boolean isBreakfastIncluded() {
        return breakfastIncluded;
    }

    @Column(name="PETS_ALLOWED")
    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    @Column(name="DATE_AVAILABLE_FROM")
    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    @ManyToOne (optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "HOTELFPR_ID")
    public Hotel getHotel() {
        return hotel;
    }

//---------------------------------------------------- set -------------------------------------------------------
    public void setId(long id) {
        this.id = id;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBreakfastIncluded(boolean breakfastIncluded) {
        this.breakfastIncluded = breakfastIncluded;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setDateAvailableFrom(Date dateAvailableFrom) {
        this.dateAvailableFrom = dateAvailableFrom;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

//---------------------------------------------------- other ---------------------------------------------------

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", numberOfGuests=" + numberOfGuests +
                ", price=" + price +
                ", breakfastIncluded=" + breakfastIncluded +
                ", petsAllowed=" + petsAllowed +
                ", dateAvailableFrom=" + dateAvailableFrom +
                ", hotel=" + hotel +
                '}';
    }
}
