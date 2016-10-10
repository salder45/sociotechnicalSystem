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
import mx.edu.um.dii.labinterfaces.diasetproject.dao.TimeStoredDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStored;
import org.hibernate.NonUniqueObjectException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author laboratoriointerface
 */
@Repository
@Transactional
public class TimeStoredDaoHibernate extends BaseDao implements TimeStoredDao{

    @Override
    public List<TimeStored> getTimesStored(TimeStored timeStored) {
        //Use an EntityManager instance to create a CriteriaBuilder object.
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        //Create a query object by creating an instance of the CriteriaQuery interface. This query object’s attributes will be modified with the details of the query.
        CriteriaQuery<TimeStored> criteriaQuery = criteriaBuilder.createQuery(TimeStored.class);
        //Set the query root by calling the from method on the CriteriaQuery object.
        Root<TimeStored> timeStoredRoot = criteriaQuery.from(TimeStored.class);//Specify what the type of the query result will be by calling the select method of the CriteriaQuery object.
        criteriaQuery.select(timeStoredRoot);
        //declare Predicate list to hold filters
        List<Predicate> criteriaList=new ArrayList<>();
        
        if(timeStored.getStatus()!=null&&!timeStored.getStatus().equals(Constants.EMPTY_STRING)){
            log.debug("Filter by status");
            Predicate predicate=criteriaBuilder.equal(timeStoredRoot.get("status"), timeStored.getStatus());
            criteriaList.add(predicate);
        }
        
        //convert list to predicate array
        Predicate[] criteriaArray=new Predicate[criteriaList.size()];
        criteriaList.toArray(criteriaArray);
        //add to query
        criteriaQuery.where(criteriaArray);
        
        //Prepare the query for execution by creating a TypedQuery<T> instance, specifying the type of the query result.
        TypedQuery<TimeStored> typedQuery = currentSession().createQuery(criteriaQuery);
        //Execute the query by calling the getResultList method on the TypedQuery<T> object. Because this query returns a collection of entities, the result is stored in a List.
        List<TimeStored> timeStoredList = typedQuery.getResultList();
        
        return timeStoredList;
    }

    @Override
    public TimeStored getLastTimeStoredByWorkOrderBy(Long workOrderId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeStored getTimeStored(Long id) {
        TimeStored timeStored=(TimeStored)currentSession().get(TimeStored.class, id);
        return timeStored;
    }

    @Override
    public TimeStored save(TimeStored timeStored) {
        timeStored.setDateCreated(new Date());
        timeStored.setLastUpdated(new Date());
        //
        currentSession().save(timeStored);
        
        return timeStored;
    }

    @Override
    public TimeStored update(TimeStored timeStored) {
        try {
            timeStored.setLastUpdated(new Date());

            currentSession().update(timeStored);
            currentSession().flush();
        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a timeStored with the same Id in session, trying to merge");
            currentSession().merge(timeStored);
            currentSession().flush();
        }
        return timeStored;
    }

    @Override
    public String delete(Long id) {
        TimeStored timeStored=getTimeStored(id);
        String strId=""+timeStored.getId();
        
        currentSession().delete(timeStored);
        currentSession().flush();
        
        return strId;
    }
    
}
