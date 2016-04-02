package com.epam.mentoring.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andrey Yun on 29.03.2016.
 */

@Entity(name = "UNIT")
public class Unit implements IdentifiedEntity {

    private Integer id;
    private String name;
    private List<Employee> employees;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "unit")
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employees=" + employees +
                '}';
    }
}
