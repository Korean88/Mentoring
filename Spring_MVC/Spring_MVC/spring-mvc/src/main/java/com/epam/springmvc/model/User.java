package com.epam.springmvc.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by Andrey Yun on 13.04.2016.
 */
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_LOGIN_PASS,
                query = "select u from com.epam.springmvc.model.User u where u.login = :login and u.password = :pass")
        })
@Entity(name = "user")
public class User {

    public static final String FIND_BY_LOGIN_PASS = "findByLoginAndPassword";

    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private List<CheckoutHistory> checkoutHistories;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "F_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "L_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @OneToMany(mappedBy = "user")
    public List<CheckoutHistory> getCheckoutHistories() {
        return checkoutHistories;
    }

    public void setCheckoutHistories(List<CheckoutHistory> checkoutHistories) {
        this.checkoutHistories = checkoutHistories;
    }
}
