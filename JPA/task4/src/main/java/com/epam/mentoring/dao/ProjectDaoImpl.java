package com.epam.mentoring.dao;

import com.epam.mentoring.model.Project;

/**
 * Created by Andrey Yun on 31.03.2016.
 */

public class ProjectDaoImpl extends EntityDao<Project> {

    public ProjectDaoImpl() {
        super(Project.class);
    }

}
