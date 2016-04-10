package com.epam.mentoring.service;

import com.epam.mentoring.dao.EntityDao;
import com.epam.mentoring.model.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andrey on 10.04.2016.
 */

@Service
public class UnitServiceImpl implements EntityService<Unit> {

    @Autowired
    private EntityDao<Unit> unitDao;

    @Override
    public Unit add(Unit entity) {
        return unitDao.add(entity);
    }

    @Override
    public Unit find(Integer id) {
        return unitDao.find(id);
    }

    @Override
    public boolean delete(Integer id) {
        return unitDao.delete(id);
    }

    @Override
    public boolean update(Integer id, Unit entity) {
        return unitDao.update(id, entity);
    }

}
