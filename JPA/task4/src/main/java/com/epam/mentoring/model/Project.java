package com.epam.mentoring.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Andrey Yun on 29.03.2016.
 */

@Entity(name = "PROJECT")
public class Project implements IdentifiedEntity {

    private Integer id;
    private String name;
    private Boolean internal;
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
    @javax.jdo.annotations.Index(name = "IDX_PROJ_NAME_UNIQ", unique = "true")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "EMPLOYEE_PROJECT",
            joinColumns = @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID"))
    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", internal=" + internal +
                ", employees=" + employees +
                '}';
    }
}
