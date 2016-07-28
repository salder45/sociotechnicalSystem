/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.UserDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.User;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laboratoriointerface
 */
@Repository
@Transactional
public class UserDaoHibernate extends BaseDao implements UserDao{
    
    
    @Override
    public User get(Long id) {
        User user=(User)currentSession().get(User.class, id);
        return user;
    }

    @Override
    public User get(String username) {
        Query query=currentSession().createQuery("select u from User u where u.username=:Username");
        query.setParameter("Username", username);
        User user=(User)query.uniqueResult();
        return user;
    }

    @Override
    public User save(User user) {
        currentSession().save(user);
        return user;
    }
    
    @Override
    public User update(User user){
        try{
        currentSession().update(user);
        currentSession().flush();
        }catch(NonUniqueObjectException nuoe){
            log.warn("Already exist a user with the same Id in session, trying to merge");
            currentSession().merge(user);
            currentSession().flush();
        }
        return user;
    }

    @Override
    public List<User> get() {
        Query query=currentSession().createQuery("select u from User u");
        List<User> list=query.getResultList();
        return list;
    }
    
}
