package com.epam.mentoring.model;

import javax.persistence.*;

/**
 * Created by Andrey Yun on 29.03.2016.
 */

@Entity(name = "EMPLOYEE_PERSONAL_INFO")
public class EmployeePersonalInfo implements IdentifiedEntity {

    private Integer id;
    private String firstName;
    private String lastName;
    private String ssn;
    private EmployeeStatus status;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    @Enumerated(EnumType.STRING)
    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeePersonalInfo{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ssn='" + ssn + '\'' +
                ", status=" + status +
                '}';
    }
}
