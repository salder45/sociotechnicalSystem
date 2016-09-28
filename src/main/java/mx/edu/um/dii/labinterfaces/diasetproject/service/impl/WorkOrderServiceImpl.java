/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.WorkOrderDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author eder
 */
@Service
public class WorkOrderServiceImpl extends BaseService implements WorkOrderService {

    @Autowired
    private WorkOrderDao workOrderDao;

    @Override
    public List<WorkOrder> getAll() {
        return workOrderDao.get(new WorkOrder());
    }

    @Override
    public WorkOrder getById(Long id) {
        return workOrderDao.get(id);
    }

    @Override
    public WorkOrder getByCode(String code) {
        return workOrderDao.getByCode(code);
    }

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        return workOrderDao.save(workOrder);
    }

    @Override
    public WorkOrder update(WorkOrder workOrder) {
        return workOrderDao.update(workOrder);        
    }

    @Override
    public String delete(Long id) {
        return workOrderDao.delete(id);
    }

}
