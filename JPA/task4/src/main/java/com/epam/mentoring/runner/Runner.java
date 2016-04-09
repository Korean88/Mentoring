package com.epam.mentoring.runner;

import com.epam.mentoring.config.SpringConfig;
import com.epam.mentoring.dao.EmployeeDaoImpl;
import com.epam.mentoring.dao.EntityDao;
import com.epam.mentoring.model.Address;
import com.epam.mentoring.model.Employee;
import com.epam.mentoring.model.EmployeePersonalInfo;
import com.epam.mentoring.model.Project;
import com.epam.mentoring.model.Unit;
import com.epam.mentoring.service.EmployeeService;
import com.epam.mentoring.service.EmployeeServiceImpl;
import com.epam.mentoring.util.JsonConverter;
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
        EmployeeService employeeService = ctx.getBean(SpringConfig.EMPLOYEE_SERVICE, EmployeeServiceImpl.class);
        JsonConverter converter = ctx.getBean(SpringConfig.JSON_CONVERTER, JsonConverter.class);

        String projectJson = "{\"id\":1,\"name\":\"Project1\",\"internal\":false}";
        String unitJson = "{\"id\":1,\"name\":\"Unit1\",\"employees\":null}";
        String personalInfoJson = "{\"id\":null,\"firstName\":\"Garry\",\"lastName\":\"Gale\",\"ssn\":\"ssn123456789\",\"status\":\"ACTIVE\"}";
        String addressJson = "{\"country\":\"USA\",\"city\":\"NYC\",\"street\":\"WALL Street\",\"houseNumber\":\"block c\",\"apartment\":\"12t\"}";

        Project project = (Project) converter.convertFromJson(projectJson, Project.class);
        projectDao.add(project);

        Unit unit = (Unit) converter.convertFromJson(unitJson, Unit.class);
        unitDao.add(unit);

        Employee employee = new Employee();
        Address address = (Address) converter.convertFromJson(addressJson, Address.class);
        EmployeePersonalInfo personalInfo = (EmployeePersonalInfo) converter.convertFromJson(personalInfoJson, EmployeePersonalInfo.class);
        employee.setAddress(address);
        employee.setPersonalInfo(personalInfo);
        employeeDao.add(employee);

        employeeService.assignEmployeeForProject(employee.getId(), project.getId());
        employeeService.addEmployeeToUnit(employee.getId(), unit.getId());

        ctx.close();
    }

}
