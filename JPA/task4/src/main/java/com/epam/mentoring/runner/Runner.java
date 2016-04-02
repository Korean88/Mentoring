package com.epam.mentoring.runner;

import com.epam.mentoring.config.SpringConfig;
import com.epam.mentoring.dao.EmployeeDaoImpl;
import com.epam.mentoring.dao.EntityDao;
import com.epam.mentoring.model.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Andrey Yun on 30.03.2016.
 */
public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);

        EntityDao<Project> projectDao = ctx.getBean(SpringConfig.PROJECT_DAO, EntityDao.class);
        EntityDao<Unit> unitDao = ctx.getBean(SpringConfig.UNIT_DAO, EntityDao.class);
        EmployeeDaoImpl employeeDao = ctx.getBean(SpringConfig.EMPLOYEE_DAO, EmployeeDaoImpl.class);

        Project project = createProject("Project1", false);
        projectDao.add(project);

        Unit unit = createUnit("Unit1");
        unitDao.add(unit);

        Employee employee = new Employee();
        Address address = createAddress("USA", "NYC", "WALL Street", "block c", "12t");
        EmployeePersonalInfo personalInfo = createPersonalInfo("Garry", "Gale", "ssn123456789", EmployeeStatus.ACTIVE);
        employee.setAddress(address);
        employee.setPersonalInfo(personalInfo);
        employeeDao.add(employee);

        employeeDao.assignEmployeeForProject(employee.getId(), project.getId());
        employeeDao.addEmployeeToUnit(employee.getId(), unit.getId());

        ctx.close();
    }

    private static Project createProject(String name, boolean isInternal) {
        Project project = new Project();
        project.setName(name);
        project.setInternal(isInternal);
        return project;
    }

    private static Unit createUnit(String name) {
        Unit unit = new Unit();
        unit.setName(name);
        return unit;
    }

    private static EmployeePersonalInfo createPersonalInfo(String firstName, String lastName, String ssn,
                                                          EmployeeStatus status) {
        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo();
        personalInfo.setFirstName(firstName);
        personalInfo.setLastName(lastName);
        personalInfo.setSsn(ssn);
        personalInfo.setStatus(status);
        return personalInfo;
    }

    private static Address createAddress(String country, String city, String street, String houseNumber, String apt) {
        Address address = new Address();
        address.setCountry(country);
        address.setCity(city);
        address.setStreet(street);
        address.setHouseNumber(houseNumber);
        address.setApartment(apt);
        return address;
    }

}
