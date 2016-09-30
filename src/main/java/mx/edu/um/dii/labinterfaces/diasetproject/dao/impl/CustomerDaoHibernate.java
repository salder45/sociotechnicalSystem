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
import mx.edu.um.dii.labinterfaces.diasetproject.dao.CustomerDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Customer;
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
public class CustomerDaoHibernate extends BaseDao implements CustomerDao {

    @Override
    public List<Customer> getCustomers(Customer customer) {
        //Use an EntityManager instance to create a CriteriaBuilder object.
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        //Create a query object by creating an instance of the CriteriaQuery interface. This query object’s attributes will be modified with the details of the query.
        CriteriaQuery<Customer> criteriaQuery = criteriaBuilder.createQuery(Customer.class);
        //Set the query root by calling the from method on the CriteriaQuery object.
        Root<Customer> customerRoot = criteriaQuery.from(Customer.class);
        //Specify what the type of the query result will be by calling the select method of the CriteriaQuery object.
        criteriaQuery.select(customerRoot);
        //declare Predicate list to hold filters
        List<Predicate> criteriaList = new ArrayList<>();
        /*Add criterias*/
 /*
         */

        if (customer.getStatus() != null && !customer.getStatus().equals(Constants.EMPTY_STRING)) {
            log.debug("Filter by status");
            Predicate predicate = criteriaBuilder.equal(customerRoot.get("status"), customer.getStatus());
            criteriaList.add(predicate);
        }

        if (customer.getName() != null && !customer.getName().equals(Constants.EMPTY_STRING)) {
            Predicate predicateName;
            Predicate predicateCode;

            Character first = customer.getName().charAt(0);
            Character last = customer.getName().charAt(customer.getName().length() - 1);
            //
            if (first == '%' && last == '%') {
                predicateName = criteriaBuilder.like(customerRoot.<String>get("name"), customer.getName());
                predicateCode = criteriaBuilder.like(customerRoot.<String>get("code"), customer.getCode());
                Predicate predicateOr=criteriaBuilder.or(predicateName,predicateCode);
                criteriaList.add(predicateOr);
            } else {
                 predicateName = criteriaBuilder.equal(customerRoot.get("name"), customer.getName());
                criteriaList.add(predicateName);
            }
        }

        //convert list to predicate array
        Predicate[] criteriaArray = new Predicate[criteriaList.size()];
        criteriaList.toArray(criteriaArray);
        //add to query
        criteriaQuery.where(criteriaArray);
        //Prepare the query for execution by creating a TypedQuery<T> instance, specifying the type of the query result.
        TypedQuery<Customer> typedQuery = currentSession().createQuery(criteriaQuery);
        //Execute the query by calling the getResultList method on the TypedQuery<T> object. Because this query returns a collection of entities, the result is stored in a List.
        List<Customer> customerList = typedQuery.getResultList();

        return customerList;

    }

    @Override
    public Customer get(Long id) {
        Customer customer = currentSession().get(Customer.class, id);
        return customer;
    }

    @Override
    public Customer get(String name) {
        Query query = currentSession().createQuery("select c from Customer c where c.name=:Name");
        query.setParameter("Name", name);

        Customer customer = (Customer) query.uniqueResult();
        return customer;
    }

    @Override
    public Customer save(Customer customer) {
        customer.setDateCreated(new Date());
        customer.setLastUpdated(new Date());
        //check where you can set the status
        customer.setStatus(Constants.STATUS_ACTIVE);

        currentSession().save(customer);
        //
        customer.setCode(ProjectUtils.generateCode(customer.getId(), Constants.CUSTOMER_START_CODE));
        update(customer);
        //
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        try {
            customer.setLastUpdated(new Date());

            currentSession().update(customer);
        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a Customer with the same Id in session, trying to merge");
            currentSession().merge(customer);
            currentSession().flush();
        }

        return customer;
    }

    @Override
    public String delete(Long id) {
        Customer customer = get(id);
        String name = customer.getName();

        currentSession().delete(customer);
        currentSession().flush();

        return name;
    }

}
