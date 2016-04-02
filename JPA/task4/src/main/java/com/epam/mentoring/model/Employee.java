package com.epam.mentoring.model;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

/**
 * Created by Andrey Yun on 29.03.2016.
 */

@Entity(name = "EMPLOYEE")
public class Employee implements IdentifiedEntity {

    private Integer id;
    private Address address;
    private EmployeePersonalInfo personalInfo;
    private List<Project> projects;
    private Unit unit;

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

    @Embedded
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public EmployeePersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(EmployeePersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    @ManyToMany(mappedBy = "employees")
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UNIT_ID")
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", address=" + address +
                ", personalInfo=" + personalInfo +
                ", projects=" + projects +
                ", unit=" + unit +
                '}';
    }
}
