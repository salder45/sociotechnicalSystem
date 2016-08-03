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

}
