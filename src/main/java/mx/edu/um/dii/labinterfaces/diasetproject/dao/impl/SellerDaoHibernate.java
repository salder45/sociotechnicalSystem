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
import mx.edu.um.dii.labinterfaces.diasetproject.dao.SellerDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;
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
public class SellerDaoHibernate extends BaseDao implements SellerDao {

    @Override
    public List<Seller> getSellers(Seller seller) {
        //Use an EntityManager instance to create a CriteriaBuilder object.
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        //Create a query object by creating an instance of the CriteriaQuery interface. This query object’s attributes will be modified with the details of the query.
        CriteriaQuery<Seller> criteriaQuery = criteriaBuilder.createQuery(Seller.class);
        //Set the query root by calling the from method on the CriteriaQuery object.
        Root<Seller> sellerRoot = criteriaQuery.from(Seller.class);
        //Specify what the type of the query result will be by calling the select method of the CriteriaQuery object.
        criteriaQuery.select(sellerRoot);
        //declare Predicate list to hold filters
        List<Predicate> criteriaList=new ArrayList<>();
        /*Add criterias*/
        /*
        
        Sample
        
        if(machine.getArea()!=null&&machine.getArea().getId()!=null&&machine.getArea().getId()!=0L){
            log.debug("Filter by area");
            Predicate predicate=criteriaBuilder.equal(machineRoot.get("area").get("Id"), machine.getArea().getId());
            criteriaList.add(predicate);
        }
        */
        if(seller.getStatus()!=null&&!seller.getStatus().equals(Constants.EMPTY_STRING)){
            log.debug("Filter by status");
            Predicate predicate=criteriaBuilder.equal(sellerRoot.get("status"), seller.getStatus());
            criteriaList.add(predicate);
        }
        
         //convert list to predicate array
        Predicate[] criteriaArray=new Predicate[criteriaList.size()];
        criteriaList.toArray(criteriaArray);
        //add to query
        criteriaQuery.where(criteriaArray);
        //Prepare the query for execution by creating a TypedQuery<T> instance, specifying the type of the query result.
        TypedQuery<Seller> typedQuery = currentSession().createQuery(criteriaQuery);
        //Execute the query by calling the getResultList method on the TypedQuery<T> object. Because this query returns a collection of entities, the result is stored in a List.
        List<Seller> sellersList = typedQuery.getResultList();

        return sellersList;
    }

    @Override
    public Seller get(Long id) {
        Seller seller = currentSession().get(Seller.class, id);
        return seller;
    }

    @Override
    public Seller get(String name) {
        Query query = currentSession().createQuery("select s from Seller s where s.name=:Name");
        query.setParameter("Name", name);

        Seller seller = (Seller) query.uniqueResult();

        return seller;
    }

    @Override
    public Seller save(Seller seller) {
        seller.setDateCreated(new Date());
        seller.setLastUpdated(new Date());
        //check where you can set the status
        seller.setStatus(Constants.STATUS_ACTIVE);
        //
        currentSession().save(seller);

        return seller;
    }

    @Override
    public Seller update(Seller seller) {
        try {
            seller.setLastUpdated(new Date());

            currentSession().update(seller);

        } catch (NonUniqueObjectException nuoe) {
            log.warn("Already exist a Seller with the same Id in session, trying to merge");
            currentSession().merge(seller);
            currentSession().flush();
        }
        
        return seller;
    }

    @Override
    public String delete(Long id) {
        Seller seller=get(id);
        String nameStrong=seller.getName();
        
        currentSession().delete(seller);
        currentSession().flush();
        
        return nameStrong;
    }

}
