/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.edu.um.dii.labinterfaces.diasetproject.service.impl;

import java.util.Date;
import java.util.List;
import mx.edu.um.dii.labinterfaces.diasetproject.config.Constants;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.AreaDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.BatchDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.CustomerDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.SellerDao;
import mx.edu.um.dii.labinterfaces.diasetproject.dao.WorkOrderDao;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Batch;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Customer;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Seller;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.MachineService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.TimeStoredService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.WorkOrderService;
import mx.edu.um.dii.labinterfaces.diasetproject.utils.ProjectUtils;
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

    @Autowired
    private SellerDao sellerDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private BatchDao batchDao;

    @Autowired
    private TimeStoredService timeStoredService;

    @Autowired
    private MachineService machineService;

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
    public WorkOrder create(WorkOrder workOrder) {
        workOrder.setEstimatedReleaseDate(ProjectUtils.getDefaultDate());
        workOrder.setReleaseDate(ProjectUtils.getDefaultDate());
        workOrder.setStatus(Constants.STATUS_CREATED);
        workOrder.setAreaActual(areaDao.getByName(Constants.DEFAULT_AREA_SELLING));
        //Customer
        if (workOrder.getCustomer() != null && workOrder.getCustomer().getId() != null && workOrder.getCustomer().getId() != 0L) {
            Customer customer = customerDao.get(workOrder.getCustomer().getId());
            workOrder.setCustomer(customer);
        }
        //Seller
        if (workOrder.getSeller() != null && workOrder.getSeller().getId() != null && workOrder.getSeller().getId() != 0L) {
            Seller seller = sellerDao.get(workOrder.getSeller().getId());
            workOrder.setSeller(seller);
        }
        workOrderDao.save(workOrder);
        //open timeStored
        timeStoredService.createTimeStored(workOrder);
        //set timeStoredArea
        timeStoredService.createTimeStoredArea(workOrder.getId(),workOrder.getAreaActual().getId(),0);
        return workOrder;
    }

    @Override
    public WorkOrder close(WorkOrder workOrder) {
        workOrder = getById(workOrder.getId());
        
        Area areaTmp=workOrder.getAreaActual();
        
        workOrder.setAreaActual(null);
        workOrder.setStatus(Constants.STATUS_CLOSED);
        workOrder.setReleaseDate(new Date());
        update(workOrder);
        //close TimeStored
        timeStoredService.closeTimeStored(workOrder);
        //close TimeStoredArea       
        timeStoredService.closeTimeStoredArea(workOrder.getId(),areaTmp.getId());
        
        return workOrder;
    }

    @Override
    public WorkOrder save(WorkOrder workOrder) {
        return workOrderDao.save(workOrder);
    }

    @Override
    public WorkOrder update(WorkOrder workOrder) {
        //Customer
        if (workOrder.getCustomer() != null && workOrder.getCustomer().getId() != null && workOrder.getCustomer().getId() != 0L) {
            Customer customer = customerDao.get(workOrder.getCustomer().getId());
            workOrder.setCustomer(customer);
        }
        //Seller
        if (workOrder.getSeller() != null && workOrder.getSeller().getId() != null && workOrder.getSeller().getId() != 0L) {
            Seller seller = sellerDao.get(workOrder.getSeller().getId());
            workOrder.setSeller(seller);
        }
        return workOrderDao.update(workOrder);
    }

    @Override
    public String delete(Long id) {
        WorkOrder workOrder = getById(id);
        for (Batch batch : workOrder.getBatchs()) {
            batchDao.delete(batch.getId());
        }
        return workOrderDao.delete(id);
    }

    @Override
    public List<WorkOrder> getByArea(Long areaId) {
        Area area = new Area();
        area.setId(areaId);
        //
        WorkOrder workOrder = new WorkOrder();
        workOrder.setAreaActual(area);
        //
        return workOrderDao.get(workOrder);
    }

    @Override
    public WorkOrder setEstimatedReleaseDate(Date releaseDate, Long workOrderId) {
        WorkOrder workOrder = getById(workOrderId);
        workOrder.setEstimatedReleaseDate(releaseDate);
        workOrder = update(workOrder);
        return workOrder;
    }

    @Override
    public WorkOrder sendToArea(Long newAreaId, Long workOrderId,Integer scrap) {
        WorkOrder workOrder = getById(workOrderId);
        Area area = areaDao.get(newAreaId);
        //check status by area
        if(area.getName().equals(Constants.DEFAULT_AREA_SELLING)){
            workOrder.setStatus(Constants.STATUS_CREATED);
        }else{
            workOrder.setStatus(Constants.STATUS_ACTIVE);
        }
        //close TimeStoredArea
        timeStoredService.closeTimeStoredArea(workOrderId, workOrder.getAreaActual().getId());
        workOrder.setAreaActual(area);
        //createdTimeStoredArea
        workOrder = update(workOrder);
        timeStoredService.createTimeStoredArea(workOrderId, newAreaId,scrap);
        return workOrder;
    }

    @Override
    public WorkOrder setToMachine(Long machineId, Long workOrderId) {
        Machine machine = machineService.getById(machineId);
        WorkOrder workOrder = getById(workOrderId);
        //set machineActual at order
        workOrder.setMachineActual(machine);
        workOrder.setStatus(Constants.STATUS_WORKING_AT);
        //set status working at machine
        machineService.setWorkingStatus(machine);

        update(workOrder);
        //
        timeStoredService.createTimeStoredMachine(workOrderId, machineId);

        return workOrder;
    }

    @Override
    public WorkOrder pullOutMachine(Long machineId, Long workOrderId) {
        Machine machine = machineService.getById(machineId);
        WorkOrder workOrder = getById(workOrderId);
        //set machineActual at order
        workOrder.setMachineActual(null);
        workOrder.setStatus(Constants.STATUS_ACTIVE);
        //set status working at machine
        machineService.setAvalaibleStatus(machine);

        update(workOrder);
        //
        timeStoredService.closeTimeStoredMachine(workOrderId, machineId);

        return workOrder;
    }

}
