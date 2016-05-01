package com.epam.mentoring.service;

import com.epam.mentoring.dao.EntityDao;
import com.epam.mentoring.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrey on 10.04.2016.
 */

@Service
public class ProjectServiceImpl implements EntityService<Project> {

    @Autowired
    private EntityDao<Project> projectDao;


    @Override
    public Project add(Project entity) {
        return projectDao.add(entity);
    }

    @Override
    public Project find(Integer id) {
        return projectDao.find(id);
    }

    @Override
    public boolean delete(Integer id) {
        return projectDao.delete(id);
    }

    @Override
    public boolean update(Integer id, Project entity) {
        return projectDao.update(id, entity);
    }
}
