package com.epam.mentoring.model;

import java.util.Set;

/**
 * Created by Andrey on 04.04.2016.
 */
public class Cashier extends Person {

    private Set<Client> clients;

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    @Override
    public String toString() {
        return "Cashier{id: " + getId() +
                ", clients=" + clients +
                '}';
    }
}
