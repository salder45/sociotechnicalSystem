/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import java.util.Date;
import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.MachineDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;
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
public class MachineDaoHibernate extends BaseDao implements MachineDao {

    @Override
    public List<Machine> getMachines(Machine machine) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Machine get(Long id) {
        Machine machine = currentSession().get(Machine.class, id);

        return machine;
    }

    @Override
    public Machine get(String code) {
        Query query = currentSession().createQuery("select m from Machine m where m.code=:Code");
        query.setParameter("Code", code);
        Machine machine = (Machine) query.uniqueResult();
        return machine;
    }

    @Override
    public Machine save(Machine machine) {
        //
        machine.setDateCreated(new Date());
        machine.setLastUpdated(new Date());
        //check where you can set the status
        machine.setStatus(Constants.STATUS_ACTIVE);
        //
        currentSession().save(machine);
        return machine;
    }

    @Override
    public Machine update(Machine machine) {
        try {
            //
            machine.setLastUpdated(new Date());
            //
            currentSession().update(machine);
        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a machine with the same Id in session, trying to merge");
            currentSession().merge(machine);
            currentSession().flush();
        }

        return machine;
    }

    @Override
    public String delete(Long id) {
        Machine machine=get(id);
        String machineName=machine.getCode()+" - "+machine.getName();
        
        currentSession().delete(machine);
        currentSession().flush();
        
        return machineName;
    }

}
