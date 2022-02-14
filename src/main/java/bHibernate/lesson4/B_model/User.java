package bHibernate.lesson4.B_model;

import bHibernate.lesson4.B_model.ENUM.UserType;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "USERFPR")
public class User {
    private long id;
    private String userName;
    private String password;
    private String country;
    private UserType userType;
    private List<Order> orders;

    public User() {
    }

    public User(long id, String userName, String password, String country, UserType userType, List<Order> orders) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.country = country;
        this.userType = userType;
        this.orders = orders;
    }

    //---------------------------------------------------- get ---------------------------------------------------------
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    public long getId() {
        return id;
    }
    @Column(name="USER_NAME")
    public String getUserName() {
        return userName;
    }

    @Column(name="PASSWORD")
    public String getPassword() {
        return password;
    }

    @Column(name="COUNTRY")
    public String getCountry() {
        return country;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    public UserType getUserType() {
        return userType;
    }

    @OneToMany (mappedBy = "user", fetch = FetchType.EAGER)
    public List<Order> getOrders() {
        return orders;
    }

//---------------------------------------------------- set ------------------------------------------------------------
    public void setId(long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", userType=" + userType +
                '}';
    }
}
