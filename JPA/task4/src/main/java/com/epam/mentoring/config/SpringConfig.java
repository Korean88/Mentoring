package com.epam.mentoring.config;

import com.epam.mentoring.dao.*;
import com.epam.mentoring.model.Employee;
import com.epam.mentoring.model.Project;
import com.epam.mentoring.model.Unit;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by Andrey Yun on 31.03.2016.
 */

@Configuration
public class SpringConfig {

    public static final String ENTITY_MANAGER_PROVIDER = "entityManagerProvider";
    public static final String PROJECT_DAO = "projectDao";
    public static final String UNIT_DAO = "unitDao";
    public static final String EMPLOYEE_DAO = "employeeDao";

    @Bean(name = ENTITY_MANAGER_PROVIDER)
    public EntityManagerProvider getEntityManagerProvider() {
        return new EntityManagerProvider();
    }

    @Bean(name = PROJECT_DAO)
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public EntityDao<Project> createProjectDao() {
        return new ProjectDaoImpl(Project.class);
    }

    @Bean(name = UNIT_DAO)
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public EntityDao<Unit> createUnitDao() {
        return new UnitDaoImpl(Unit.class);
    }

    @Bean(name = EMPLOYEE_DAO)
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public EmployeeDaoImpl createEmployeeDao() {
        return new EmployeeDaoImpl(Employee.class);
    }
}
