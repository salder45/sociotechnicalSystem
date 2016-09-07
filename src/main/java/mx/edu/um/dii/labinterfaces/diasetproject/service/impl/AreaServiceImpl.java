/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.AreaDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.service.AreaService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class AreaServiceImpl extends BaseService implements AreaService{

    @Autowired
    private AreaDao areaDao;
    
    @Override
    public List<Area> getAll() {
        return areaDao.getAll();
    }

    @Override
    public Area get(Long id) {
        return areaDao.get(id);
    }

    @Override
    public Area get(String code) {
        return areaDao.getByCode(code);
    }

    @Override
    public Area save(Area area) {
        return areaDao.save(area);
    }

    @Override
    public Area update(Area area) {
        return areaDao.update(area);
    }

    @Override
    public String delete(Long id) {
        return areaDao.delete(id);
    }
    
}
