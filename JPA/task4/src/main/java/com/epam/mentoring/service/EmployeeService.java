package com.epam.mentoring.service;

/**
 * Created by Andrey on 09.04.2016.
 */
public interface EmployeeService {

    boolean addEmployeeToUnit(Integer employeeId, Integer unitId);

    boolean assignEmployeeForProject(Integer employeeId, Integer projectId);

}
