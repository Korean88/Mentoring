package com.epam.mentoring.model;

import javax.persistence.Embeddable;

/**
 * Created by Andrey Yun on 29.03.2016.
 */

@Embeddable
public class Address {

    private String country;
    private String city;
    private String street;
    private String houseNumber;
    private short apartmentNumber;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public short getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(short apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }
}
