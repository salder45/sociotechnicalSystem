/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.InicializaDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.RoleDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laboratoriointerface
 */
@Repository
@Transactional
public class InicializaDaoHibernate extends BaseDao implements InicializaDao{

    @Autowired
    RoleDao roleDao;
    
    @Override
    public void inicializa(String username, String password) {
        log.debug("Init data......");
        //Create Roles
         Role roleAdmin=roleDao.get(Constants.ROLE_ADMIN);
         log.debug("Role={}",roleAdmin);
         if(roleAdmin==null||roleAdmin.getAuthority()==null||roleAdmin.getAuthority().equals("")){
             roleAdmin=new Role(Constants.ROLE_ADMIN);
             roleDao.save(roleAdmin);
         }
        //Create admin/user user
    }

    @Override
    public void inicializaRoles(String username, String password, String roles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
