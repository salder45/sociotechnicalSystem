/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.RoleDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Role;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class RoleServiceImpl extends BaseService implements RoleService{

    @Autowired
    private RoleDao roleDao;
    
    @Override
    public List<Role> getAll() {
        return roleDao.get();
    }
    
}
