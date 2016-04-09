package com.epam.mentoring.dao;

import com.epam.mentoring.model.Employee;

/**
 * Created by Andrey Yun on 02.04.2016.
 */
public class EmployeeDaoImpl extends EntityDao<Employee> {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

}
