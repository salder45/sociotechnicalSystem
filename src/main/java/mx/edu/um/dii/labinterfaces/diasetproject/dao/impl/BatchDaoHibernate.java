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
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BatchDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Batch;
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
public class BatchDaoHibernate extends BaseDao implements BatchDao {

    @Override
    public List<Batch> get(Batch batch) {
        //Use an EntityManager instance to create a CriteriaBuilder object.
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        //Create a query object by creating an instance of the CriteriaQuery interface. This query object’s attributes will be modified with the details of the query.
        CriteriaQuery<Batch> criteriaQuery = criteriaBuilder.createQuery(Batch.class);
        //Set the query root by calling the from method on the CriteriaQuery object.
        Root<Batch> batchRoot = criteriaQuery.from(Batch.class);//Specify what the type of the query result will be by calling the select method of the CriteriaQuery object.
        criteriaQuery.select(batchRoot);
        //declare Predicate list to hold filters
        List<Predicate> criteriaList=new ArrayList<>();
        
        if(batch.getWorkOrder()!=null&&batch.getWorkOrder().getId()!=null&&batch.getWorkOrder().getId()!=0L){
            log.debug("Filter by workOrder");
            Predicate predicate=criteriaBuilder.equal(batchRoot.get("workOrder").get("Id"), batch.getWorkOrder().getId());
            criteriaList.add(predicate);
        }
        
        
        //convert list to predicate array
        Predicate[] criteriaArray=new Predicate[criteriaList.size()];
        criteriaList.toArray(criteriaArray);
        //add to query
        criteriaQuery.where(criteriaArray);
        
        //Prepare the query for execution by creating a TypedQuery<T> instance, specifying the type of the query result.
        TypedQuery<Batch> typedQuery = currentSession().createQuery(criteriaQuery);
        //Execute the query by calling the getResultList method on the TypedQuery<T> object. Because this query returns a collection of entities, the result is stored in a List.
        List<Batch> batchList = typedQuery.getResultList();

        return batchList;
    }

    @Override
    public Batch get(Long id) {
        Batch batch = (Batch) currentSession().get(Batch.class, id);
        return batch;
    }

    @Override
    public Batch getByDescription(String description) {
        Query query = currentSession().createQuery("select b from Batch b where b.description=:Description");
        query.setParameter("Description", description);

        Batch batch = (Batch) query.uniqueResult();
        return batch;
    }

    @Override
    public Batch save(Batch batch) {
        batch.setDateCreated(new Date());
        batch.setLastUpdated(new Date());
        batch.setStatus(Constants.STATUS_ACTIVE);

        currentSession().save(batch);

        return batch;
    }

    @Override
    public Batch update(Batch batch) {
        try {
            batch.setLastUpdated(new Date());

            currentSession().update(batch);
            currentSession().flush();
            
        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a batch with the same Id in session, trying to merge");
            currentSession().merge(batch);
            currentSession().flush();
        }
        
        return batch;
    }

    @Override
    public String delete(Long id) {
        Batch batch = get(id);
        String name = batch.getDescription();

        currentSession().delete(batch);
        currentSession().flush();

        return name;
    }

}
