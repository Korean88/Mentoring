package com.epam.springmvc.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey Yun on 13.04.2016.
 */
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_LOGIN_PASS,
                query = "select u from com.epam.springmvc.model.User u where u.login = :login and u.password = :pass"),
        @NamedQuery(name = User.FIND_BY_LOGIN,
                query = "select u from com.epam.springmvc.model.User u where u.login = :login")
        })
@Entity(name = "user")
public class User {

    public static final String FIND_BY_LOGIN_PASS = "findByLoginAndPassword";
    public static final String FIND_BY_LOGIN = "findByLogin";

    private Integer id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private List<CheckoutHistory> checkoutHistories;
    private Set<Role> roles = new HashSet<>();

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE",
            joinColumns = { @JoinColumn(name = "USER_ID", referencedColumnName = "ID") },
            inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (checkoutHistories != null ? !checkoutHistories.equals(user.checkoutHistories) : user.checkoutHistories != null)
            return false;
        if (!firstName.equals(user.firstName)) return false;
        if (!id.equals(user.id)) return false;
        if (!lastName.equals(user.lastName)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;
        if (roles != null ? !roles.equals(user.roles) : user.roles != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (checkoutHistories != null ? checkoutHistories.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", checkoutHistories=" + checkoutHistories +
                ", roles=" + roles +
                '}';
    }
}
