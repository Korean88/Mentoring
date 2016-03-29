package com.epam.mentoring.model;

import javax.persistence.*;

/**
 * Created by Andrey Yun on 29.03.2016.
 */

@Entity(name = "EMPLOYEE_PERSONAL_INFO")
public class EmployeePersonalInfo {

    private Integer id;
    private Employee employee;
    private String ssn;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(mappedBy = "personalInfo")
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
