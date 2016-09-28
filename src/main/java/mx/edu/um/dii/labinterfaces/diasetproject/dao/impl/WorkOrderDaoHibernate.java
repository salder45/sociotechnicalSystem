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
import mx.edu.um.dii.labinterfaces.diasetproject.dao.WorkOrderDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
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
public class WorkOrderDaoHibernate extends BaseDao implements WorkOrderDao {

    @Override
    public List<WorkOrder> get(WorkOrder workOrder) {
        //Use an EntityManager instance to create a CriteriaBuilder object.
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        //Create a query object by creating an instance of the CriteriaQuery interface. This query object’s attributes will be modified with the details of the query.
        CriteriaQuery<WorkOrder> criteriaQuery = criteriaBuilder.createQuery(WorkOrder.class);
        //Set the query root by calling the from method on the CriteriaQuery object.
        Root<WorkOrder> workOrderRoot = criteriaQuery.from(WorkOrder.class);//Specify what the type of the query result will be by calling the select method of the CriteriaQuery object.
        criteriaQuery.select(workOrderRoot);
        //declare Predicate list to hold filters
        List<Predicate> criteriaList=new ArrayList<>();
        /*        
        */
        
        if(workOrder.getStatus()!=null&&!workOrder.getStatus().equals(Constants.EMPTY_STRING)){
            log.debug("Filter by status");
            Predicate predicate=criteriaBuilder.equal(workOrderRoot.get("status"), workOrder.getStatus());
            criteriaList.add(predicate);
        }
        
        //convert list to predicate array
        Predicate[] criteriaArray=new Predicate[criteriaList.size()];
        criteriaList.toArray(criteriaArray);
        //add to query
        criteriaQuery.where(criteriaArray);
        
        //Prepare the query for execution by creating a TypedQuery<T> instance, specifying the type of the query result.
        TypedQuery<WorkOrder> typedQuery = currentSession().createQuery(criteriaQuery);
        //Execute the query by calling the getResultList method on the TypedQuery<T> object. Because this query returns a collection of entities, the result is stored in a List.
        List<WorkOrder> workOrdersList = typedQuery.getResultList();

        return workOrdersList;
    }

    @Override
    public WorkOrder get(Long id) {
        WorkOrder workOrder = (WorkOrder) currentSession().get(WorkOrder.class, id);
        return workOrder;
    }

    @Override
    public WorkOrder getByCode(String code) {
        Query query = currentSession().createQuery("select w from WorkOrder w where w.code=:Code");
        query.setParameter("Code", code);
        WorkOrder workOrder = (WorkOrder) query.uniqueResult();
        return workOrder;
    }

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        workOrder.setDateCreated(new Date());
        workOrder.setLastUpdated(new Date());
        workOrder.setStatus(Constants.STATUS_ACTIVE);
        //
        currentSession().save(workOrder);
        //
        workOrder.setCode(ProjectUtils.generateCode(workOrder.getId(), Constants.WORKORDER_START_CODE));
        update(workOrder);
        //
        return workOrder;
    }

    @Override
    public WorkOrder update(WorkOrder workOrder) {
        try {
            workOrder.setLastUpdated(new Date());

            currentSession().update(workOrder);
            currentSession().flush();
        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a workOrder with the same Id in session, trying to merge");
            currentSession().merge(workOrder);
            currentSession().flush();
        }

        return workOrder;
    }

    @Override
    public String delete(Long id) {
        WorkOrder workOrder = get(id);
        String workOrderCode = workOrder.getCode();

        currentSession().delete(workOrder);
        currentSession().flush();

        return workOrderCode;
    }
}
