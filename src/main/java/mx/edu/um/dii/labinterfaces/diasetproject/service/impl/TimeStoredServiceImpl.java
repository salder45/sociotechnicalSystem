/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.Date;
import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.TimeStoredDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStored;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredArea;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredMachine;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.TimeStoredService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.ProjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author laboratoriointerface
 */
@Service
public class TimeStoredServiceImpl extends BaseService implements TimeStoredService {

    @Autowired
    private TimeStoredDao timeStoredDao;
    
    @Autowired
    private WorkOrderService workOrderService;
    
    @Override
    public TimeStored createTimeStored(WorkOrder workOrder) {
        TimeStored timeStored=new TimeStored();
        workOrder=workOrderService.getById(workOrder.getId());
        timeStored.setWorkOrder(workOrder);
        timeStored.setStartTime(new Date());
        timeStored.setFinishTime(ProjectUtils.getDefaultDate());
        timeStored.setStatus(Constants.STATUS_ACTIVE);
                
        timeStoredDao.save(timeStored);
                
        return timeStored;
    }

    @Override
    public TimeStored closeTimeStored(WorkOrder workOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeStoredArea createTimeStoredArea(WorkOrder workOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeStoredArea closeTimeStoredArea(WorkOrder workOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeStoredMachine createTimeStoredMachine(WorkOrder workOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TimeStoredMachine closeTimeStoredMachine(WorkOrder workOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    

}
