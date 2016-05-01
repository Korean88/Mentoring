package com.epam.mentoring.service;

import com.epam.mentoring.dao.EntityDao;
import com.epam.mentoring.model.Employee;
import com.epam.mentoring.model.Project;
import com.epam.mentoring.model.Unit;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created by Andrey on 09.04.2016.
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = Logger.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EntityDao<Project> projectDao;

    @Autowired
    private EntityDao<Unit> unitDao;

    @Autowired
    private EntityDao<Employee> employeeDao;

    @Override
    public boolean addEmployeeToUnit(Integer employeeId, Integer unitId) {
        Employee employee = employeeDao.find(employeeId);
        if (employee == null) {
            LOG.warn("An employee with id [" + employeeId + "] was not found");
            return false;
        }
        Unit unit = unitDao.find(unitId);
        if (unit == null) {
            LOG.warn("A unit with id [" + unitId + "] was not found");
            return false;
        }
        employee.setUnit(unit);
        boolean res = employeeDao.update(employeeId, employee);
        if (res) {
            LOG.info("Employee " + employee + " was added to the unit " + unit);
            return res;
        }
        return false;
    }

    @Override
    public boolean assignEmployeeForProject(Integer employeeId, Integer projectId) {
        Project project = projectDao.find(projectId);
        if (project == null) {
            LOG.warn("A project with id [" + projectId + "] was not found");
            return false;
        }
        Employee employee = employeeDao.find(employeeId);
        if (employee == null) {
            LOG.warn("An employee with id [" + employeeId + "] was not found");
            return false;
        }

        if (project.getEmployees() == null) {
            project.setEmployees(Arrays.asList(employee));
        } else {
            project.getEmployees().add(employee);
        }
        boolean res = projectDao.update(projectId, project);
        if (res) {
            LOG.info("Employee " + employee + " was assigned for project " + project);
            return res;
        }
        return true;
    }

    @Override
    public Employee add(Employee entity) {
        return employeeDao.add(entity);
    }

    @Override
    public Employee find(Integer id) {
        return employeeDao.find(id);
    }

    @Override
    public boolean delete(Integer id) {
        return employeeDao.delete(id);
    }

    @Override
    public boolean update(Integer id, Employee entity) {
        return employeeDao.update(id, entity);
    }
}
