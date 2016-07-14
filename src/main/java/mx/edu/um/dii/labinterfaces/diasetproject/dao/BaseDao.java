/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao;

import java.util.Locale;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laboratoriointerface
 */
public abstract class BaseDao {
    protected final transient Logger log=LoggerFactory.getLogger(getClass());
    protected Locale local=new java.util.Locale(Constants.LOCALE_LANGUAGE,Constants.LOCALE_COUNTRY,Constants.LOCALE_VARIANT);
    
    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;
    
    @Transactional
    protected Session currentSession(){
        return sessionFactory.getCurrentSession();
    }
    
    @Transactional
    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }
}
