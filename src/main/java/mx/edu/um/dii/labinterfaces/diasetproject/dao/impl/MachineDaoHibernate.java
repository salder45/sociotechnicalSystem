/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BaseDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.MachineDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;
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
public class MachineDaoHibernate extends BaseDao implements MachineDao {

    @Override
    public List<Machine> getMachines(Machine machine) {
        //Use an EntityManager instance to create a CriteriaBuilder object.
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        //Create a query object by creating an instance of the CriteriaQuery interface. This query object’s attributes will be modified with the details of the query.
        CriteriaQuery<Machine> criteriaQuery = criteriaBuilder.createQuery(Machine.class);
        //Set the query root by calling the from method on the CriteriaQuery object.
        Root<Machine> machineRoot = criteriaQuery.from(Machine.class);
        //Specify what the type of the query result will be by calling the select method of the CriteriaQuery object.
        criteriaQuery.select(machineRoot);
        //declare Predicate list to hold filters
        List<Predicate> criteriaList=new ArrayList<>();
        /*
        Use predicate for add stuff
         */
        if(machine.getArea()!=null&&machine.getArea().getId()!=null&&machine.getArea().getId()!=0L){
            log.debug("Filter by area");
            Predicate predicate=criteriaBuilder.equal(machineRoot.get("area").get("Id"), machine.getArea().getId());
            criteriaList.add(predicate);
        }
        //convert list to predicate array
        Predicate[] criteriaArray=new Predicate[criteriaList.size()];
        criteriaList.toArray(criteriaArray);
        //add to query
        criteriaQuery.where(criteriaArray);
        //Prepare the query for execution by creating a TypedQuery<T> instance, specifying the type of the query result.
        TypedQuery<Machine> typedQuery = currentSession().createQuery(criteriaQuery);
        //Execute the query by calling the getResultList method on the TypedQuery<T> object. Because this query returns a collection of entities, the result is stored in a List.
        List<Machine> machinesList = typedQuery.getResultList();

        return machinesList;
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
        machine.setStatus(Constants.STATUS_IDLE);
        //
        currentSession().save(machine);
        //
        machine.setCode(ProjectUtils.generateCode(machine.getId(), Constants.MACHINE_START_CODE));
        update(machine);
        //
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
        Machine machine = get(id);
        String machineName = machine.getCode() + " - " + machine.getName();

        currentSession().delete(machine);
        currentSession().flush();

        return machineName;
    }

}
