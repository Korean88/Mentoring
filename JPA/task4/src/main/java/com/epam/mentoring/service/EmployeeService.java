package com.epam.mentoring.service;

import com.epam.mentoring.model.Employee;

/**
 * Created by Andrey on 09.04.2016.
 */
public interface EmployeeService extends EntityService<Employee> {

    boolean addEmployeeToUnit(Integer employeeId, Integer unitId);

    boolean assignEmployeeForProject(Integer employeeId, Integer projectId);

}
