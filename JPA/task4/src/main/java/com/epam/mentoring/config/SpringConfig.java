package com.epam.mentoring.config;

import com.epam.mentoring.dao.EmployeeDaoImpl;
import com.epam.mentoring.dao.EntityDao;
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
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

/**
 * Created by Andrey Yun on 31.03.2016.
 */

@Configuration
@EnableTransactionManagement
public class SpringConfig {

    public static final String PROJECT_DAO = "projectDao";
    public static final String UNIT_DAO = "unitDao";
    public static final String EMPLOYEE_DAO = "employeeDao";
    public static final String JSON_CONVERTER = "jsonConverter";
    public static final String EMPLOYEE_SERVICE = "employeeService";
    private static final String EMBEDDED_DB_PATH = "db/employees.odb";

    @Bean
    public LocalEntityManagerFactoryBean createEntityManagerFactory() {
        LocalEntityManagerFactoryBean lemfb = new LocalEntityManagerFactoryBean();
        lemfb.setPersistenceUnitName(EMBEDDED_DB_PATH);
        return lemfb;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
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
