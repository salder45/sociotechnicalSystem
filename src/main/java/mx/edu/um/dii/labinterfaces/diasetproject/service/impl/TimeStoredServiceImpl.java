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
import mx.edu.um.dii.labinterfaces.diasetproject.model.Area;
import mx.edu.um.dii.labinterfaces.diasetproject.model.Machine;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStored;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredArea;
import mx.edu.um.dii.labinterfaces.diasetproject.model.TimeStoredMachine;
import mx.edu.um.dii.labinterfaces.diasetproject.model.WorkOrder;
import mx.edu.um.dii.labinterfaces.diasetproject.service.AreaService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.BaseService;
import mx.edu.um.dii.labinterfaces.diasetproject.service.MachineService;
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
    @Autowired
    private AreaService areaService;
    @Autowired
    private MachineService machineService;

    @Override
    public TimeStored createTimeStored(WorkOrder workOrder) {
        log.debug("createTimeStored");
        TimeStored timeStored = new TimeStored();
        workOrder = workOrderService.getById(workOrder.getId());
        timeStored.setWorkOrder(workOrder);
        timeStored.setStartTime(new Date());
        timeStored.setFinishTime(ProjectUtils.getDefaultDate());
        timeStored.setStatus(Constants.STATUS_ACTIVE);

        timeStoredDao.save(timeStored);

        return timeStored;
    }

    @Override
    public TimeStored closeTimeStored(WorkOrder workOrder) {
        log.debug("closeTimeStored");
        TimeStored timeStored = new TimeStored();
        workOrder = workOrderService.getById(workOrder.getId());
        for (TimeStored ts : workOrder.getTimesStored()) {
            if (Constants.TYPE_TIMESTORED.equals(ts.getType()) && ts.getStatus().equals(Constants.STATUS_ACTIVE)) {
                timeStored = ts;
            }
        }
        timeStored = timeStoredDao.getTimeStored(timeStored.getId());
        timeStored.setFinishTime(new Date());
        timeStored.setStatus(Constants.STATUS_CLOSED);

        timeStoredDao.update(timeStored);

        return timeStored;
    }

    @Override
    public TimeStoredArea createTimeStoredArea(Long workOrderId, Long areaId) {
        log.debug("createTimeStoredArea");
        //get data
        WorkOrder workOrder = workOrderService.getById(workOrderId);
        Area area = areaService.get(areaId);
        //create
        TimeStoredArea timeStoredArea = new TimeStoredArea();
        timeStoredArea.setWorkOrder(workOrder);
        timeStoredArea.setStartTime(new Date());
        timeStoredArea.setFinishTime(ProjectUtils.getDefaultDate());
        timeStoredArea.setStatus(Constants.STATUS_ACTIVE);
        timeStoredArea.setArea(area);
        //
        timeStoredDao.save(timeStoredArea);

        return timeStoredArea;

    }

    @Override
    public TimeStoredArea closeTimeStoredArea(Long workOrderId, Long areaId) {
        log.debug("closeTimeStoredArea");
        WorkOrder workOrder = workOrderService.getById(workOrderId);
        Area area = areaService.get(areaId);
        
        TimeStoredArea timeStoredArea = new TimeStoredArea();
        for (TimeStored ts : workOrder.getTimesStored()) {
            if (Constants.TYPE_TIMESTORED_AREA.equals(ts.getType())) {
                TimeStoredArea tmp = (TimeStoredArea) ts;
                if (tmp.getStatus().equals(Constants.STATUS_ACTIVE) && tmp.getArea().getId().equals(area.getId())) {
                    timeStoredArea = tmp;
                }
            }
        }
        timeStoredArea = (TimeStoredArea) timeStoredDao.getTimeStored(timeStoredArea.getId());

        timeStoredArea.setFinishTime(new Date());
        timeStoredArea.setStatus(Constants.STATUS_CLOSED);

        timeStoredDao.update(timeStoredArea);

        return timeStoredArea;
    }

    @Override
    public TimeStoredMachine createTimeStoredMachine(Long workOrderId,Long machineId) {
        log.debug("createTimeStoredMachine");
        Machine machine=machineService.getById(machineId);
        WorkOrder workOrder=workOrderService.getById(workOrderId);
        Area area=areaService.get(machine.getArea().getId());
        
        TimeStoredMachine timeStoredMachine = new TimeStoredMachine();
        timeStoredMachine.setWorkOrder(workOrder);
        timeStoredMachine.setStartTime(new Date());
        timeStoredMachine.setFinishTime(ProjectUtils.getDefaultDate());
        timeStoredMachine.setStatus(Constants.STATUS_ACTIVE);
        timeStoredMachine.setMachine(machine);
        timeStoredMachine.setArea(area);
        
        timeStoredDao.save(timeStoredMachine);
        
        return timeStoredMachine;        
    }

    @Override
    public TimeStoredMachine closeTimeStoredMachine(Long workOrderId,Long machineId) {
        log.debug("closeTimeStoredMachine");
        Machine machine=machineService.getById(machineId);
        WorkOrder workOrder=workOrderService.getById(workOrderId);
        
        TimeStoredMachine timeStoredMachine=new TimeStoredMachine();
        
        for (TimeStored ts : workOrder.getTimesStored()) {
            if (Constants.TYPE_TIMESTORED_MACHINE.equals(ts.getType())) {
                TimeStoredMachine tmp=(TimeStoredMachine)ts;
                if (tmp.getStatus().equals(Constants.STATUS_ACTIVE) && tmp.getMachine().getId().equals(machine.getId())) {
                    timeStoredMachine=tmp;
                }
            }
        }
        
        timeStoredMachine=(TimeStoredMachine)timeStoredDao.getTimeStored(timeStoredMachine.getId());
        
        timeStoredMachine.setFinishTime(new Date());
        timeStoredMachine.setStatus(Constants.STATUS_CLOSED);
        
        timeStoredDao.update(timeStoredMachine);
        
        return timeStoredMachine;        
    }

}
