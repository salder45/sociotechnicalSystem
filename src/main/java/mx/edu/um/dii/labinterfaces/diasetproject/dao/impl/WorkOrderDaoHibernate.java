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
import mx.edu.um.dii.labinterfaces.diasetproject.dao.WorkOrderDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.ProjectUtils;
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
    public List<WorkOrder> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
