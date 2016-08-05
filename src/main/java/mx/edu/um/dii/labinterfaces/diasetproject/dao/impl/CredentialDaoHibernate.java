/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.CredentialDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Credential;
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
public class CredentialDaoHibernate extends BaseDao implements CredentialDao {

    @Override
    public Credential get(Long id) {
        Credential credential = (Credential) currentSession().get(Credential.class, id);
        return credential;
    }

    @Override
    public Credential get(String barcode) {
        Query query=currentSession().createQuery("select c from Credential c where c.barcodeValue=:Barcode");
        query.setParameter("Barcode", barcode);
        Credential credential=(Credential)query.uniqueResult();
        return credential;
    }

    @Override
    public Credential save(Credential credential) {
        currentSession().save(credential);
        return credential;
    }

    @Override
    public Credential update(Credential credential) {
        try {
            currentSession().update(credential);
            currentSession().flush();
        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a credential with the same Id in session, trying to merge");
            currentSession().merge(credential);
            currentSession().flush();
        }
        return credential;
    }

    @Override
    public String delete(Long id) {
        Credential credential=get(id);
        String barcode=credential.getBarcodeValue();
        currentSession().delete(credential);
        currentSession().flush();        
        return barcode;
    }

}
