package com.epam.mentoring.runner;

import com.epam.mentoring.model.Address;
import com.epam.mentoring.model.Employee;
import com.epam.mentoring.model.EmployeePersonalInfo;
import com.epam.mentoring.model.Project;
import com.epam.mentoring.model.Unit;
import com.epam.mentoring.service.EmployeeService;
import com.epam.mentoring.service.EntityService;
import com.epam.mentoring.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Andrey on 10.04.2016.
 */
public class EntityManipulator {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EntityService<Project> projectService;

    @Autowired
    private EntityService<Unit> unitService;

    @Autowired
    private JsonConverter converter;

    public void createEntities() {
        String projectJson = "{\"id\":1,\"name\":\"Project1\",\"internal\":false}";
        String unitJson = "{\"id\":1,\"name\":\"Unit1\",\"employees\":null}";
        String personalInfoJson = "{\"id\":null,\"firstName\":\"Garry\",\"lastName\":\"Gale\",\"ssn\":\"ssn123456789\",\"status\":\"ACTIVE\"}";
        String addressJson = "{\"country\":\"USA\",\"city\":\"NYC\",\"street\":\"WALL Street\",\"houseNumber\":\"block c\",\"apartment\":\"12t\"}";

        Project project = (Project) converter.convertFromJson(projectJson, Project.class);
        projectService.add(project);

        Unit unit = (Unit) converter.convertFromJson(unitJson, Unit.class);
        unitService.add(unit);

        Employee employee = new Employee();
        Address address = (Address) converter.convertFromJson(addressJson, Address.class);
        EmployeePersonalInfo personalInfo = (EmployeePersonalInfo) converter.convertFromJson(personalInfoJson, EmployeePersonalInfo.class);
        employee.setAddress(address);
        employee.setPersonalInfo(personalInfo);
        employeeService.add(employee);

        employeeService.assignEmployeeForProject(employee.getId(), project.getId());
        employeeService.addEmployeeToUnit(employee.getId(), unit.getId());
    }
}
