package com.epam.springmvc.model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by Andrey Yun on 13.04.2016.
 */
@NamedQueries({
        @NamedQuery(name = Meal.FIND_ALL,
                query = "select m from com.epam.springmvc.model.Meal m")
})
@Entity(name = "meal")
public class Meal {

    public static final String FIND_ALL = "findAll";

    private Integer id;
    private String name;
    private Boolean vegetarian;
    private Boolean diabetic;
    private BigDecimal price;
    private String imagePath;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "VEGETARIAN")
    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Column(name = "DIABETIC")
    public Boolean getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(Boolean diabetic) {
        this.diabetic = diabetic;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "IMG_PATH")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
