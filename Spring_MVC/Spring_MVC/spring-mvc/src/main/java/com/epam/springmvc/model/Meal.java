package com.epam.springmvc.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Andrey Yun on 13.04.2016.
 */
@NamedQueries({
        @NamedQuery(name = Meal.FIND_ALL,
                query = "select m from com.epam.springmvc.model.Meal m"),
        @NamedQuery(name = Meal.FIND_BY_ID,
                query = "select m from com.epam.springmvc.model.Meal m where m.id=:id")
})
@Entity(name = "meal")
@XmlRootElement
public class Meal implements Serializable {

    public static final String FIND_ALL = "findAll";
    public static final String FIND_BY_ID = "findById";
    private static final long serialVersionUID = 5392546333124136345L;

    private Integer id;
    private String name;
    private Boolean vegetarian;
    private Boolean diabetic;
    private BigDecimal price;
    private String imagePath;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @XmlElement
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "NAME")
    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "VEGETARIAN")
    @XmlElement
    public Boolean getVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(Boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Column(name = "DIABETIC")
    @XmlElement
    public Boolean getDiabetic() {
        return diabetic;
    }

    public void setDiabetic(Boolean diabetic) {
        this.diabetic = diabetic;
    }

    @Column(name = "PRICE")
    @XmlElement
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "IMG_PATH")
    @XmlElement
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;

        Meal meal = (Meal) o;

        if (id != null ? !id.equals(meal.id) : meal.id != null) return false;
        if (name != null ? !name.equals(meal.name) : meal.name != null) return false;
        if (vegetarian != null ? !vegetarian.equals(meal.vegetarian) : meal.vegetarian != null) return false;
        if (diabetic != null ? !diabetic.equals(meal.diabetic) : meal.diabetic != null) return false;
        if (price != null ? !price.equals(meal.price) : meal.price != null) return false;
        return imagePath != null ? imagePath.equals(meal.imagePath) : meal.imagePath == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (vegetarian != null ? vegetarian.hashCode() : 0);
        result = 31 * result + (diabetic != null ? diabetic.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (imagePath != null ? imagePath.hashCode() : 0);
        return result;
    }
}
