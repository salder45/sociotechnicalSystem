/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BatchDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Batch;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class BatchServiceImpl extends BaseService implements BatchService {

    @Autowired
    private BatchDao batchDao;
    @Override
    public List<Batch> getAll() {
        Batch batch=new Batch();
        return batchDao.get(batch);
    }

    @Override
    public List<Batch> getBatchsListByWorkOrderAndStatus(Long workOrderId, String status) {
        WorkOrder workOrder=new WorkOrder();
        workOrder.setId(workOrderId);
        
        Batch batch=new Batch();
        batch.setWorkOrder(workOrder);
        batch.setStatus(status);
        
        return batchDao.get(batch);
    }
    
    @Override
    public Batch getById(Long id) {
        return batchDao.get(id);
    }

    @Override
    public Batch save(Batch batch) {
        return batchDao.save(batch);
    }

    @Override
    public Batch update(Batch batch) {
        return batchDao.update(batch);
    }

    @Override
    public String delete(Long id) {
        return batchDao.delete(id);
    }

    
    
}
