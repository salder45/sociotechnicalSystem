/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.RoleDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Role;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laboratoriointerface
 */
@Repository
@Transactional
public class RoleDaoHibernate extends BaseDao implements RoleDao{

    public RoleDaoHibernate(){
        log.info("New RoleDaoHibernate instance create");
    }
    
    @Override
    @Transactional(readOnly = true)
    public Role get(Long id) {
        Role role=(Role)currentSession().get(Role.class, id);        
        return role;
    }

    @Override
    public Role get(String authority) {
        log.debug("Authority={}",authority);
        Query query=currentSession().createQuery("select r from Role r where authority=:Role");
        query.setParameter("Role", authority);
        Role role=(Role) query.uniqueResult();
        return role;
    }

    @Override
    public Role save(Role role) {
        currentSession().save(role);
        return role;
    }
    
}
