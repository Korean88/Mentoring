package com.epam.mentoring.dao;

/**
 * Created by Andrey Yun on 31.03.2016.
 */
public interface EmployeeDao {

    boolean addEmployeeToUnit(Integer employeeId, Integer unitId);
    boolean assignEmployeeForProject(Integer employeeId, Integer projectId);

}
