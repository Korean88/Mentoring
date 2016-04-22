package com.epam.springmvc.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrey Yun on 14.04.2016.
 */

@Entity(name = "cart_item")
public class CheckoutHistory {

    private Integer id;
    private User user;
    private Meal meal;
    private int quantity;
    private Date dateAdded;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.REMOVE})
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToOne
    @JoinColumn(name = "MEAL_ID")
    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    @Column(name = "QTY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(name = "DATE_ADDED")
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
