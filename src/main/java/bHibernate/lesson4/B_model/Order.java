package bHibernate.lesson4.B_model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "ORDERFPR")
public class Order {
    private long id;
    private User user;
    private Room room;
    private Date dateFrom;
    private Date dateTo;
    private double moneyPaid;

    public Order() {
    }

    public Order(long id, User user, Room room, Date dateFrom, Date dateTo, double moneyPaid) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.moneyPaid = moneyPaid;
    }

//---------------------------------------------------- get ----------------------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    public long getId() {
        return id;
    }

    @ManyToOne (optional = false,cascade = CascadeType.ALL)
    @JoinColumn(name = "USERFPR_ID")                       //не уверен
    public User getUser() {
        return user;
    }

    @OneToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "ROOMFPR_ID")
    public Room getRoom() {
        return room;
    }

    @Column (name = "DATE_FROM")
    public Date getDateFrom() {
        return dateFrom;
    }

    @Column (name = "DATE_TO")
    public Date getDateTo() {
        return dateTo;
    }

    @Column (name = "MONEY_PAID")
    public double getMoneyPaid() {
        return moneyPaid;
    }

//---------------------------------------------------- set ----------------------------------------------------------
    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public void setMoneyPaid(double moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

//---------------------------------------------------- other --------------------------------------------------------
    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", moneyPaid=" + moneyPaid +
                '}';
    }
}
