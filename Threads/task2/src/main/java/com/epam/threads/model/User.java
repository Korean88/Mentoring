package com.epam.threads.model;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Andrey Yun on 28.01.16.
 */
public class User implements Serializable {

    private int id;
    private String name;
    private Set<String> accountNames;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getAccountNames() {
        return accountNames;
    }

    public void setAccountNames(Set<String> accountNames) {
        this.accountNames = accountNames;
    }

    public User() {
    }

    public User(int id, String name, Set<Account> accounts) {
        this.id = id;
        this.name = name;
        this.accountNames = accountNames;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountNames=" + accountNames +
                '}';
    }
}
