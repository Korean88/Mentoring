package com.epam.mentoring.config;

import com.epam.mentoring.dao.EmployeeDaoImpl;
import com.epam.mentoring.dao.EntityDao;
import com.epam.mentoring.dao.EntityManagerProvider;
import com.epam.mentoring.dao.ProjectDaoImpl;
import com.epam.mentoring.dao.UnitDaoImpl;
import com.epam.mentoring.model.Project;
import com.epam.mentoring.model.Unit;
import com.epam.mentoring.service.EmployeeService;
import com.epam.mentoring.service.EmployeeServiceImpl;
import com.epam.mentoring.util.JsonConverter;
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
    public static final String JSON_CONVERTER = "jsonConverter";
    public static final String EMPLOYEE_SERVICE = "employeeService";

    @Bean(name = ENTITY_MANAGER_PROVIDER)
    public EntityManagerProvider getEntityManagerProvider() {
        return new EntityManagerProvider();
    }

    @Bean(name = PROJECT_DAO)
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public EntityDao<Project> createProjectDao() {
        return new ProjectDaoImpl();
    }

    @Bean(name = UNIT_DAO)
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public EntityDao<Unit> createUnitDao() {
        return new UnitDaoImpl();
    }

    @Bean(name = EMPLOYEE_DAO)
    @Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
    public EmployeeDaoImpl createEmployeeDao() {
        return new EmployeeDaoImpl();
    }

    @Bean(name = JSON_CONVERTER)
    public JsonConverter createJsonConverter() {
        return new JsonConverter();
    }

    @Bean(name = EMPLOYEE_SERVICE)
    public EmployeeService createEmployeeService() {
        return new EmployeeServiceImpl();
    }

}
