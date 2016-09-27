/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import java.util.Date;
import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.AreaDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.ProjectUtils;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laboratoriointerface
 */
@Repository
@Transactional
public class AreaDaoHibernate extends BaseDao implements AreaDao {

    @Override
    public List<Area> getAll() {
        Query query = currentSession().createQuery("select a from Area a");
        List<Area> list = query.getResultList();
        return list;
    }

    @Override
    public Area get(Long id) {
        Area area = (Area) currentSession().get(Area.class, id);
        return area;
    }

    @Override
    public Area getByCode(String code) {
        Query query = currentSession().createQuery("select a from Area a where a.code=:Code");
        query.setParameter("Code", code);
        Area area = (Area) query.uniqueResult();
        return area;
    }

    @Override
    public Area save(Area area) {
        //set values
        area.setDateCreated(new Date());
        area.setLastUpdated(new Date());
        area.setStatus(Constants.STATUS_ACTIVE);
        //TODO CREATE CODE FOR THE AREA
        
        currentSession().save(area);
        //
        area.setCode(ProjectUtils.generateCode(area.getId(), Constants.AREA_START_CODE));
        update(area);
        //
        return area;
    }

    @Override
    public Area update(Area area) {
        try {
            //set values
            area.setLastUpdated(new Date());
            
            currentSession().update(area);
            currentSession().flush();
        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a area with the same Id in session, trying to merge");
            currentSession().merge(area);
            currentSession().flush();
        }

        return area;
    }

    @Override
    public String delete(Long id) {
        Area area=get(id);
        String name=area.getCode()+"-"+area.getName();
        
        currentSession().delete(area);
        currentSession().flush();
        
        return name;
    }

}
